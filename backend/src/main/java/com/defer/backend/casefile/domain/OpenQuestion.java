package com.defer.backend.casefile.domain;

import java.time.Instant;
import java.util.UUID;

public class OpenQuestion {

    private UUID id;
    private UUID caseFileId;
    private String questionText;
    private QuestionStatus status;
    private ActionSource source;
    private Instant createdAt;
    private Instant updatedAt;

    public OpenQuestion() {}

    public OpenQuestion(UUID id, UUID caseFileId, String questionText, QuestionStatus status,
                        ActionSource source, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.caseFileId = caseFileId;
        this.questionText = questionText;
        this.status = status;
        this.source = source;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getCaseFileId() { return caseFileId; }
    public void setCaseFileId(UUID caseFileId) { this.caseFileId = caseFileId; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public QuestionStatus getStatus() { return status; }
    public void setStatus(QuestionStatus status) { this.status = status; }

    public ActionSource getSource() { return source; }
    public void setSource(ActionSource source) { this.source = source; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
