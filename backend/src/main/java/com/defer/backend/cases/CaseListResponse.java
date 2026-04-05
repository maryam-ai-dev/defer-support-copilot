package com.defer.backend.cases;

import java.time.Instant;
import java.util.UUID;

public record CaseListResponse(
        UUID caseFileId,
        UUID conversationId,
        String status,
        String resolutionMode,
        String issueSummary,
        double currentFrustrationScore,
        double currentEffortScore,
        boolean escalationCandidate,
        Instant updatedAt
) {}
