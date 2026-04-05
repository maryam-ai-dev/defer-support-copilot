package com.defer.backend.decision.application;

import com.defer.backend.casefile.application.CaseFileApplicationService;
import com.defer.backend.casefile.domain.CaseFile;
import com.defer.backend.decision.contracts.AiServiceResponse;
import com.defer.backend.decision.domain.DecisionContext;
import com.defer.backend.decision.domain.DecisionOutcome;
import com.defer.backend.decision.domain.service.ResolutionModeSelector;
import com.defer.backend.decision.infrastructure.DecisionLogEntity;
import com.defer.backend.decision.infrastructure.DecisionRepositoryJpa;
import com.defer.backend.policy.application.PolicyApplicationService;
import com.defer.backend.policy.domain.SupportPolicy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class DecisionApplicationService {

    private final PolicyApplicationService policyService;
    private final CaseFileApplicationService caseFileService;
    private final DecisionRepositoryJpa decisionLogRepo;
    private final ObjectMapper objectMapper;

    public DecisionApplicationService(PolicyApplicationService policyService,
                                       CaseFileApplicationService caseFileService,
                                       DecisionRepositoryJpa decisionLogRepo,
                                       ObjectMapper objectMapper) {
        this.policyService = policyService;
        this.caseFileService = caseFileService;
        this.decisionLogRepo = decisionLogRepo;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public DecisionOutcome evaluateIncomingTurn(UUID conversationId, UUID messageId,
                                                 AiServiceResponse aiResponse) {
        // 1. Load active policy
        SupportPolicy policy = policyService.getActivePolicy();

        // 2. Load CaseFile for repetition count
        CaseFile caseFile = caseFileService.getCaseFileByConversationId(conversationId);

        // 3. Build DecisionContext
        double loopScore = aiResponse.decisionSignals().loopDetected() ? 1.0 : 0.0;

        DecisionContext ctx = new DecisionContext(
                conversationId,
                caseFile.getId(),
                messageId,
                aiResponse.retrievalConfidence(),
                aiResponse.customerState().frustrationScore(),
                aiResponse.customerState().effortScore(),
                aiResponse.customerState().trustRiskScore(),
                loopScore,
                caseFile.getRepetitionCount(),
                aiResponse.suggestedMode(),
                policy
        );

        // 4. Apply rules
        DecisionOutcome outcome = ResolutionModeSelector.select(ctx);

        // 5. Persist decision log
        persistDecisionLog(ctx, outcome);

        return outcome;
    }

    private void persistDecisionLog(DecisionContext ctx, DecisionOutcome outcome) {
        DecisionLogEntity entity = new DecisionLogEntity();
        entity.setId(UUID.randomUUID());
        entity.setConversationId(ctx.conversationId());
        entity.setMessageId(ctx.messageId());
        entity.setRetrievalConfidence(BigDecimal.valueOf(ctx.retrievalConfidence()));
        entity.setFrustrationScore(BigDecimal.valueOf(ctx.frustrationScore()));
        entity.setEffortScore(BigDecimal.valueOf(ctx.effortScore()));
        entity.setTrustRiskScore(BigDecimal.valueOf(ctx.trustRiskScore()));
        entity.setLoopScore(BigDecimal.valueOf(ctx.loopScore()));
        entity.setRepetitionCount(ctx.repetitionCount());
        entity.setSuggestedMode(ctx.suggestedMode());
        entity.setSelectedMode(outcome.selectedMode().name());
        entity.setCreatedAt(Instant.now());

        try {
            entity.setRationaleJson(objectMapper.writeValueAsString(outcome.rationale()));
        } catch (JsonProcessingException e) {
            entity.setRationaleJson("[]");
        }

        decisionLogRepo.save(entity);
    }
}
