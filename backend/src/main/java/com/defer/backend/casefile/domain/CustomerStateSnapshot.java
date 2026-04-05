package com.defer.backend.casefile.domain;

import java.time.Instant;
import java.util.UUID;

public class CustomerStateSnapshot {

    private UUID id;
    private UUID caseFileId;
    private UUID messageId;
    private double frustrationScore;
    private double confusionScore;
    private double effortScore;
    private double trustRiskScore;
    private double degradationScore;
    private Instant createdAt;

    public CustomerStateSnapshot() {}

    public CustomerStateSnapshot(UUID id, UUID caseFileId, UUID messageId,
                                  double frustrationScore, double confusionScore,
                                  double effortScore, double trustRiskScore,
                                  double degradationScore, Instant createdAt) {
        this.id = id;
        this.caseFileId = caseFileId;
        this.messageId = messageId;
        this.frustrationScore = frustrationScore;
        this.confusionScore = confusionScore;
        this.effortScore = effortScore;
        this.trustRiskScore = trustRiskScore;
        this.degradationScore = degradationScore;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getCaseFileId() { return caseFileId; }
    public void setCaseFileId(UUID caseFileId) { this.caseFileId = caseFileId; }

    public UUID getMessageId() { return messageId; }
    public void setMessageId(UUID messageId) { this.messageId = messageId; }

    public double getFrustrationScore() { return frustrationScore; }
    public void setFrustrationScore(double frustrationScore) { this.frustrationScore = frustrationScore; }

    public double getConfusionScore() { return confusionScore; }
    public void setConfusionScore(double confusionScore) { this.confusionScore = confusionScore; }

    public double getEffortScore() { return effortScore; }
    public void setEffortScore(double effortScore) { this.effortScore = effortScore; }

    public double getTrustRiskScore() { return trustRiskScore; }
    public void setTrustRiskScore(double trustRiskScore) { this.trustRiskScore = trustRiskScore; }

    public double getDegradationScore() { return degradationScore; }
    public void setDegradationScore(double degradationScore) { this.degradationScore = degradationScore; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
