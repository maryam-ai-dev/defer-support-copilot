package com.defer.backend.evaluation.application;

import com.defer.backend.evaluation.contracts.EvalRunPayload;
import com.defer.backend.evaluation.contracts.EvalRunResponse;
import com.defer.backend.evaluation.infrastructure.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class EvalApplicationService {

    private final EvalRunRepositoryJpa runRepo;
    private final EvalResultRepositoryJpa resultRepo;
    private final ObjectMapper objectMapper;

    public EvalApplicationService(EvalRunRepositoryJpa runRepo,
                                   EvalResultRepositoryJpa resultRepo,
                                   ObjectMapper objectMapper) {
        this.runRepo = runRepo;
        this.resultRepo = resultRepo;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public EvalRunResponse persistEvalRun(EvalRunPayload payload) {
        UUID runId = UUID.fromString(payload.runId());

        EvalRunEntity run = new EvalRunEntity();
        run.setId(runId);
        run.setName(payload.name());
        run.setStartedAt(Instant.parse(payload.startedAt() + "Z"));
        run.setEndedAt(Instant.parse(payload.endedAt() + "Z"));

        try {
            run.setMetricsJson(objectMapper.writeValueAsString(payload.metrics()));
        } catch (JsonProcessingException e) {
            run.setMetricsJson("{}");
        }

        runRepo.save(run);

        for (EvalRunPayload.EvalResultItem item : payload.results()) {
            EvalResultEntity result = new EvalResultEntity();
            result.setId(UUID.randomUUID());
            result.setEvalRunId(runId);
            result.setScenarioId(item.scenarioId());
            result.setDescription(item.description());
            result.setExpectedMode(item.expectedMode());
            result.setActualMode(item.actualMode());
            result.setPassed(item.passed());
            result.setTurnCount(item.turnCount());
            result.setCreatedAt(Instant.now());
            resultRepo.save(result);
        }

        return getEvalRun(runId);
    }

    @Transactional(readOnly = true)
    public List<EvalRunResponse> listEvalRuns() {
        return runRepo.findAll(Sort.by(Sort.Direction.DESC, "startedAt")).stream()
                .map(r -> buildResponse(r, resultRepo.findByEvalRunId(r.getId())))
                .toList();
    }

    @Transactional(readOnly = true)
    public EvalRunResponse getEvalRun(UUID runId) {
        EvalRunEntity run = runRepo.findById(runId)
                .orElseThrow(() -> new IllegalArgumentException("Eval run not found: " + runId));
        List<EvalResultEntity> results = resultRepo.findByEvalRunId(runId);
        return buildResponse(run, results);
    }

    private EvalRunResponse buildResponse(EvalRunEntity run, List<EvalResultEntity> results) {
        List<EvalRunResponse.EvalResultResponse> items = results.stream()
                .map(r -> new EvalRunResponse.EvalResultResponse(
                        r.getId(), r.getScenarioId(), r.getDescription(),
                        r.getExpectedMode(), r.getActualMode(), r.isPassed(), r.getTurnCount()))
                .toList();

        return new EvalRunResponse(
                run.getId(), run.getName(), run.getMetricsJson(),
                run.getStartedAt(), run.getEndedAt(), items);
    }
}
