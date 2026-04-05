package com.defer.backend.evaluation.contracts;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record EvalRunResponse(
        UUID id,
        String name,
        String metricsJson,
        Instant startedAt,
        Instant endedAt,
        List<EvalResultResponse> results
) {
    public record EvalResultResponse(
            UUID id,
            String scenarioId,
            String description,
            String expectedMode,
            String actualMode,
            boolean passed,
            Integer turnCount
    ) {}
}
