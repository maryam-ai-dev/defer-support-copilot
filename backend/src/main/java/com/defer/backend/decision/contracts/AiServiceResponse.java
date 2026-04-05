package com.defer.backend.decision.contracts;

import java.util.List;

public record AiServiceResponse(
        String suggestedMode,
        String responseDraft,
        double retrievalConfidence,
        CustomerState customerState,
        MemoryUpdate memoryUpdate,
        DecisionSignals decisionSignals
) {
    public record CustomerState(
            double frustrationScore,
            double confusionScore,
            double effortScore,
            double trustRiskScore,
            boolean isRepeated,
            double repetitionSimilarity
    ) {}

    public record MemoryUpdate(
            String updatedIssueSummary,
            String customerGoal,
            List<String> attemptedActions,
            List<String> openQuestions
    ) {}

    public record DecisionSignals(
            boolean loopDetected,
            boolean repetitionDetected,
            boolean clarificationNeeded
    ) {}
}
