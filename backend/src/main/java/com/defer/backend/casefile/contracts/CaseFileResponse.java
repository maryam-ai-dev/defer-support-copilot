package com.defer.backend.casefile.contracts;

import java.time.Instant;
import java.util.UUID;

public record CaseFileResponse(
        UUID id,
        UUID conversationId,
        UUID customerId,
        String status,
        String issueSummary,
        String customerGoal,
        String currentResolutionMode,
        boolean escalationCandidate,
        int repetitionCount,
        double currentFrustrationScore,
        double currentConfusionScore,
        double currentEffortScore,
        double currentTrustRiskScore,
        String lastAiActionSummary,
        Instant createdAt,
        Instant updatedAt
) {}
