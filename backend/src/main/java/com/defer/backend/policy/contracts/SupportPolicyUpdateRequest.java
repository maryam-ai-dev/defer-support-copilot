package com.defer.backend.policy.contracts;

import java.math.BigDecimal;

public record SupportPolicyUpdateRequest(
        BigDecimal escalationFrustrationThreshold,
        BigDecimal escalationEffortThreshold,
        Integer escalationRepetitionCount,
        BigDecimal minConfidenceForDirectAnswer,
        BigDecimal requiresReviewConfidenceFloor,
        Boolean sensitiveTopicsEnabled
) {}
