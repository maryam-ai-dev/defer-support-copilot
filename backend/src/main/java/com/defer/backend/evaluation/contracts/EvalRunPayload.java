package com.defer.backend.evaluation.contracts;

import java.util.List;
import java.util.Map;

public record EvalRunPayload(
        String runId,
        String name,
        String startedAt,
        String endedAt,
        Map<String, Object> metrics,
        List<EvalResultItem> results
) {
    public record EvalResultItem(
            String scenarioId,
            String description,
            String expectedMode,
            String actualMode,
            boolean passed,
            int turnCount
    ) {}
}
