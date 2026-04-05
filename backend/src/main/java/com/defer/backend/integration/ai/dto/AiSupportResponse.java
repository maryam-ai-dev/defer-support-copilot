package com.defer.backend.integration.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record AiSupportResponse(
        @JsonProperty("suggested_mode") String suggestedMode,
        @JsonProperty("response_draft") String responseDraft,
        List<Citation> citations,
        @JsonProperty("retrieval_confidence") double retrievalConfidence,
        @JsonProperty("customer_state") CustomerState customerState,
        @JsonProperty("memory_update") MemoryUpdate memoryUpdate,
        @JsonProperty("decision_signals") DecisionSignals decisionSignals
) {
    public record Citation(
            @JsonProperty("document_id") UUID documentId,
            @JsonProperty("chunk_id") UUID chunkId,
            String label
    ) {}

    public record CustomerState(
            @JsonProperty("frustration_score") double frustrationScore,
            @JsonProperty("confusion_score") double confusionScore,
            @JsonProperty("effort_score") double effortScore,
            @JsonProperty("trust_risk_score") double trustRiskScore,
            @JsonProperty("is_repeated") boolean isRepeated,
            @JsonProperty("repetition_similarity") double repetitionSimilarity
    ) {}

    public record MemoryUpdate(
            @JsonProperty("updated_issue_summary") String updatedIssueSummary,
            @JsonProperty("customer_goal") String customerGoal,
            @JsonProperty("attempted_actions") List<String> attemptedActions,
            @JsonProperty("open_questions") List<String> openQuestions
    ) {}

    public record DecisionSignals(
            @JsonProperty("loop_detected") boolean loopDetected,
            @JsonProperty("repetition_detected") boolean repetitionDetected,
            @JsonProperty("clarification_needed") boolean clarificationNeeded
    ) {}
}
