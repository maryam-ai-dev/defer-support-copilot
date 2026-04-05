package com.defer.backend.policy.domain;

import java.time.Instant;
import java.util.UUID;

public class SupportPolicy {

    private UUID id;
    private double escalationFrustrationThreshold;
    private double escalationEffortThreshold;
    private int escalationRepetitionCount;
    private double minConfidenceForDirectAnswer;
    private double requiresReviewConfidenceFloor;
    private boolean sensitiveTopicsEnabled;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public double getEscalationFrustrationThreshold() { return escalationFrustrationThreshold; }
    public void setEscalationFrustrationThreshold(double v) { this.escalationFrustrationThreshold = v; }

    public double getEscalationEffortThreshold() { return escalationEffortThreshold; }
    public void setEscalationEffortThreshold(double v) { this.escalationEffortThreshold = v; }

    public int getEscalationRepetitionCount() { return escalationRepetitionCount; }
    public void setEscalationRepetitionCount(int v) { this.escalationRepetitionCount = v; }

    public double getMinConfidenceForDirectAnswer() { return minConfidenceForDirectAnswer; }
    public void setMinConfidenceForDirectAnswer(double v) { this.minConfidenceForDirectAnswer = v; }

    public double getRequiresReviewConfidenceFloor() { return requiresReviewConfidenceFloor; }
    public void setRequiresReviewConfidenceFloor(double v) { this.requiresReviewConfidenceFloor = v; }

    public boolean isSensitiveTopicsEnabled() { return sensitiveTopicsEnabled; }
    public void setSensitiveTopicsEnabled(boolean v) { this.sensitiveTopicsEnabled = v; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
