package com.defer.backend.policy.infrastructure;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "support_policies")
public class SupportPolicyEntity {

    @Id
    private UUID id;

    @Column(name = "escalation_frustration_threshold")
    private BigDecimal escalationFrustrationThreshold;

    @Column(name = "escalation_effort_threshold")
    private BigDecimal escalationEffortThreshold;

    @Column(name = "escalation_repetition_count")
    private int escalationRepetitionCount;

    @Column(name = "min_confidence_for_direct_answer")
    private BigDecimal minConfidenceForDirectAnswer;

    @Column(name = "requires_review_confidence_floor")
    private BigDecimal requiresReviewConfidenceFloor;

    @Column(name = "sensitive_topics_enabled")
    private boolean sensitiveTopicsEnabled;

    private boolean active;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public BigDecimal getEscalationFrustrationThreshold() { return escalationFrustrationThreshold; }
    public void setEscalationFrustrationThreshold(BigDecimal v) { this.escalationFrustrationThreshold = v; }

    public BigDecimal getEscalationEffortThreshold() { return escalationEffortThreshold; }
    public void setEscalationEffortThreshold(BigDecimal v) { this.escalationEffortThreshold = v; }

    public int getEscalationRepetitionCount() { return escalationRepetitionCount; }
    public void setEscalationRepetitionCount(int v) { this.escalationRepetitionCount = v; }

    public BigDecimal getMinConfidenceForDirectAnswer() { return minConfidenceForDirectAnswer; }
    public void setMinConfidenceForDirectAnswer(BigDecimal v) { this.minConfidenceForDirectAnswer = v; }

    public BigDecimal getRequiresReviewConfidenceFloor() { return requiresReviewConfidenceFloor; }
    public void setRequiresReviewConfidenceFloor(BigDecimal v) { this.requiresReviewConfidenceFloor = v; }

    public boolean isSensitiveTopicsEnabled() { return sensitiveTopicsEnabled; }
    public void setSensitiveTopicsEnabled(boolean v) { this.sensitiveTopicsEnabled = v; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
