package com.defer.backend.policy.contracts;

import java.time.Instant;
import java.util.UUID;

public record SupportPolicyResponse(
        UUID id,
        double escalationFrustrationThreshold,
        double escalationEffortThreshold,
        int escalationRepetitionCount,
        double minConfidenceForDirectAnswer,
        double requiresReviewConfidenceFloor,
        boolean sensitiveTopicsEnabled,
        Instant createdAt,
        Instant updatedAt
) {}
