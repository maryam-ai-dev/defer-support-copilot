package com.defer.backend.handoff.domain;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HandoffPacket {

    private UUID id;
    private UUID caseFileId;
    private UUID conversationId;
    private String escalationReason;
    private String issueSummary;
    private String customerGoal;
    private List<String> stepsAttempted;
    private List<String> unresolvedItems;
    private Map<String, Double> customerState;
    private String suggestedNextAction;
    private Instant createdAt;

    public HandoffPacket() {}

    public HandoffPacket(UUID id, UUID caseFileId, UUID conversationId,
                         String escalationReason, String issueSummary, String customerGoal,
                         List<String> stepsAttempted, List<String> unresolvedItems,
                         Map<String, Double> customerState, String suggestedNextAction,
                         Instant createdAt) {
        this.id = id;
        this.caseFileId = caseFileId;
        this.conversationId = conversationId;
        this.escalationReason = escalationReason;
        this.issueSummary = issueSummary;
        this.customerGoal = customerGoal;
        this.stepsAttempted = stepsAttempted;
        this.unresolvedItems = unresolvedItems;
        this.customerState = customerState;
        this.suggestedNextAction = suggestedNextAction;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getCaseFileId() { return caseFileId; }
    public void setCaseFileId(UUID caseFileId) { this.caseFileId = caseFileId; }

    public UUID getConversationId() { return conversationId; }
    public void setConversationId(UUID conversationId) { this.conversationId = conversationId; }

    public String getEscalationReason() { return escalationReason; }
    public void setEscalationReason(String escalationReason) { this.escalationReason = escalationReason; }

    public String getIssueSummary() { return issueSummary; }
    public void setIssueSummary(String issueSummary) { this.issueSummary = issueSummary; }

    public String getCustomerGoal() { return customerGoal; }
    public void setCustomerGoal(String customerGoal) { this.customerGoal = customerGoal; }

    public List<String> getStepsAttempted() { return stepsAttempted; }
    public void setStepsAttempted(List<String> stepsAttempted) { this.stepsAttempted = stepsAttempted; }

    public List<String> getUnresolvedItems() { return unresolvedItems; }
    public void setUnresolvedItems(List<String> unresolvedItems) { this.unresolvedItems = unresolvedItems; }

    public Map<String, Double> getCustomerState() { return customerState; }
    public void setCustomerState(Map<String, Double> customerState) { this.customerState = customerState; }

    public String getSuggestedNextAction() { return suggestedNextAction; }
    public void setSuggestedNextAction(String suggestedNextAction) { this.suggestedNextAction = suggestedNextAction; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
