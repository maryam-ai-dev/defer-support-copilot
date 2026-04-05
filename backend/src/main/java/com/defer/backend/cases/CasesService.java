package com.defer.backend.cases;

import com.defer.backend.casefile.infrastructure.CaseFileEntity;
import com.defer.backend.casefile.infrastructure.CaseFileRepositoryJpa;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CasesService {

    private final CaseFileRepositoryJpa caseFileRepo;

    public CasesService(CaseFileRepositoryJpa caseFileRepo) {
        this.caseFileRepo = caseFileRepo;
    }

    @Transactional(readOnly = true)
    public List<CaseListResponse> listCases(String status, Boolean escalationCandidate,
                                             Double minEffortScore) {
        List<CaseFileEntity> all = caseFileRepo.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));

        return all.stream()
                .filter(e -> status == null || status.equalsIgnoreCase(e.getStatus()))
                .filter(e -> escalationCandidate == null || e.isEscalationCandidate() == escalationCandidate)
                .filter(e -> minEffortScore == null
                        || (e.getCurrentEffortScore() != null
                            && e.getCurrentEffortScore().doubleValue() >= minEffortScore))
                .map(this::toResponse)
                .toList();
    }

    private CaseListResponse toResponse(CaseFileEntity e) {
        String summary = e.getIssueSummary();
        if (summary != null && summary.length() > 200) {
            summary = summary.substring(0, 200) + "...";
        }

        return new CaseListResponse(
                e.getId(),
                e.getConversationId(),
                e.getStatus(),
                e.getCurrentResolutionMode(),
                summary,
                e.getCurrentFrustrationScore() != null ? e.getCurrentFrustrationScore().doubleValue() : 0.0,
                e.getCurrentEffortScore() != null ? e.getCurrentEffortScore().doubleValue() : 0.0,
                e.isEscalationCandidate(),
                e.getUpdatedAt()
        );
    }
}
