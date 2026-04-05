package com.defer.backend.casefile.domain;

import java.time.Instant;
import java.util.UUID;

public class CaseFile {

    private UUID id;
    private UUID conversationId;
    private UUID customerId;
    private CaseStatus status;
    private String issueSummary;
    private String customerGoal;
    private ResolutionMode currentResolutionMode;
    private boolean escalationCandidate;
    private int repetitionCount;
    private double currentFrustrationScore;
    private double currentConfusionScore;
    private double currentEffortScore;
    private double currentTrustRiskScore;
    private String lastAiActionSummary;
    private Instant createdAt;
    private Instant updatedAt;

    public CaseFile() {}

    public CaseFile(UUID id, UUID conversationId, UUID customerId) {
        this.id = id;
        this.conversationId = conversationId;
        this.customerId = customerId;
        this.status = CaseStatus.OPEN;
        this.escalationCandidate = false;
        this.repetitionCount = 0;
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    // --- Domain methods ---

    public void updateIssueSummary(String summary) {
        this.issueSummary = summary;
        this.updatedAt = Instant.now();
    }

    public void updateCustomerGoal(String goal) {
        this.customerGoal = goal;
        this.updatedAt = Instant.now();
    }

    public void applyStateSnapshot(CustomerStateSnapshot snapshot) {
        this.currentFrustrationScore = snapshot.getFrustrationScore();
        this.currentConfusionScore = snapshot.getConfusionScore();
        this.currentEffortScore = snapshot.getEffortScore();
        this.currentTrustRiskScore = snapshot.getTrustRiskScore();
        this.updatedAt = Instant.now();
    }

    public void incrementRepetition() {
        this.repetitionCount++;
        this.updatedAt = Instant.now();
    }

    public void markEscalationCandidate() {
        this.escalationCandidate = true;
        this.updatedAt = Instant.now();
    }

    public void setResolutionMode(ResolutionMode mode) {
        this.currentResolutionMode = mode;
        this.updatedAt = Instant.now();
    }

    public void resolve() {
        this.status = CaseStatus.RESOLVED;
        this.updatedAt = Instant.now();
    }

    // --- Getters and setters ---

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getConversationId() { return conversationId; }
    public void setConversationId(UUID conversationId) { this.conversationId = conversationId; }

    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }

    public CaseStatus getStatus() { return status; }
    public void setStatus(CaseStatus status) { this.status = status; }

    public String getIssueSummary() { return issueSummary; }
    public void setIssueSummary(String issueSummary) { this.issueSummary = issueSummary; }

    public String getCustomerGoal() { return customerGoal; }
    public void setCustomerGoal(String customerGoal) { this.customerGoal = customerGoal; }

    public ResolutionMode getCurrentResolutionMode() { return currentResolutionMode; }
    public void setCurrentResolutionMode(ResolutionMode currentResolutionMode) { this.currentResolutionMode = currentResolutionMode; }

    public boolean isEscalationCandidate() { return escalationCandidate; }
    public void setEscalationCandidate(boolean escalationCandidate) { this.escalationCandidate = escalationCandidate; }

    public int getRepetitionCount() { return repetitionCount; }
    public void setRepetitionCount(int repetitionCount) { this.repetitionCount = repetitionCount; }

    public double getCurrentFrustrationScore() { return currentFrustrationScore; }
    public void setCurrentFrustrationScore(double currentFrustrationScore) { this.currentFrustrationScore = currentFrustrationScore; }

    public double getCurrentConfusionScore() { return currentConfusionScore; }
    public void setCurrentConfusionScore(double currentConfusionScore) { this.currentConfusionScore = currentConfusionScore; }

    public double getCurrentEffortScore() { return currentEffortScore; }
    public void setCurrentEffortScore(double currentEffortScore) { this.currentEffortScore = currentEffortScore; }

    public double getCurrentTrustRiskScore() { return currentTrustRiskScore; }
    public void setCurrentTrustRiskScore(double currentTrustRiskScore) { this.currentTrustRiskScore = currentTrustRiskScore; }

    public String getLastAiActionSummary() { return lastAiActionSummary; }
    public void setLastAiActionSummary(String lastAiActionSummary) { this.lastAiActionSummary = lastAiActionSummary; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
