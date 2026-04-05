package com.defer.backend.casefile.contracts;

import java.util.UUID;

public record SnapshotCustomerStateRequest(
        UUID messageId,
        double frustrationScore,
        double confusionScore,
        double effortScore,
        double trustRiskScore,
        double degradationScore
) {}
