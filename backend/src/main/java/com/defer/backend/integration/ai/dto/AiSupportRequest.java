package com.defer.backend.integration.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record AiSupportRequest(
        @JsonProperty("trace_id") UUID traceId,
        @JsonProperty("conversation_id") UUID conversationId,
        @JsonProperty("case_file_id") UUID caseFileId,
        @JsonProperty("latest_message") LatestMessage latestMessage,
        @JsonProperty("recent_history") List<HistoryEntry> recentHistory,
        @JsonProperty("case_summary") CaseSummary caseSummary
) {
    public record LatestMessage(
            @JsonProperty("message_id") UUID messageId,
            String role,
            String text
    ) {}

    public record HistoryEntry(
            String role,
            String text
    ) {}

    public record CaseSummary(
            @JsonProperty("issue_summary") String issueSummary,
            @JsonProperty("customer_goal") String customerGoal,
            @JsonProperty("attempted_actions") List<String> attemptedActions,
            @JsonProperty("open_questions") List<String> openQuestions,
            @JsonProperty("current_state") Map<String, Object> currentState
    ) {}
}
