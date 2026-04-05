package com.defer.backend.casefile.contracts;

public record RecordAttemptedActionRequest(
        String actionType,
        String actionSummary,
        String outcome,
        String source
) {}
