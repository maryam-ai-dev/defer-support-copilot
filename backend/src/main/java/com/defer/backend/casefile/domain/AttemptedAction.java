package com.defer.backend.casefile.domain;

import java.time.Instant;
import java.util.UUID;

public class AttemptedAction {

    private UUID id;
    private UUID caseFileId;
    private ActionType actionType;
    private String actionSummary;
    private ActionOutcome outcome;
    private ActionSource source;
    private Instant createdAt;

    public AttemptedAction() {}

    public AttemptedAction(UUID id, UUID caseFileId, ActionType actionType, String actionSummary,
                           ActionOutcome outcome, ActionSource source, Instant createdAt) {
        this.id = id;
        this.caseFileId = caseFileId;
        this.actionType = actionType;
        this.actionSummary = actionSummary;
        this.outcome = outcome;
        this.source = source;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getCaseFileId() { return caseFileId; }
    public void setCaseFileId(UUID caseFileId) { this.caseFileId = caseFileId; }

    public ActionType getActionType() { return actionType; }
    public void setActionType(ActionType actionType) { this.actionType = actionType; }

    public String getActionSummary() { return actionSummary; }
    public void setActionSummary(String actionSummary) { this.actionSummary = actionSummary; }

    public ActionOutcome getOutcome() { return outcome; }
    public void setOutcome(ActionOutcome outcome) { this.outcome = outcome; }

    public ActionSource getSource() { return source; }
    public void setSource(ActionSource source) { this.source = source; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
