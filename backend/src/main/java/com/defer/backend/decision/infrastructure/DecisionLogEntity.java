package com.defer.backend.decision.infrastructure;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "decision_logs")
public class DecisionLogEntity {

    @Id
    private UUID id;

    @Column(name = "conversation_id")
    private UUID conversationId;

    @Column(name = "message_id")
    private UUID messageId;

    @Column(name = "retrieval_confidence")
    private BigDecimal retrievalConfidence;

    @Column(name = "ambiguity_score")
    private BigDecimal ambiguityScore;

    @Column(name = "frustration_score")
    private BigDecimal frustrationScore;

    @Column(name = "effort_score")
    private BigDecimal effortScore;

    @Column(name = "trust_risk_score")
    private BigDecimal trustRiskScore;

    @Column(name = "loop_score")
    private BigDecimal loopScore;

    @Column(name = "repetition_count")
    private Integer repetitionCount;

    @Column(name = "suggested_mode")
    private String suggestedMode;

    @Column(name = "selected_mode")
    private String selectedMode;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "rationale_json", columnDefinition = "jsonb")
    private String rationaleJson;

    @Column(name = "created_at")
    private Instant createdAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getConversationId() { return conversationId; }
    public void setConversationId(UUID conversationId) { this.conversationId = conversationId; }

    public UUID getMessageId() { return messageId; }
    public void setMessageId(UUID messageId) { this.messageId = messageId; }

    public BigDecimal getRetrievalConfidence() { return retrievalConfidence; }
    public void setRetrievalConfidence(BigDecimal v) { this.retrievalConfidence = v; }

    public BigDecimal getAmbiguityScore() { return ambiguityScore; }
    public void setAmbiguityScore(BigDecimal v) { this.ambiguityScore = v; }

    public BigDecimal getFrustrationScore() { return frustrationScore; }
    public void setFrustrationScore(BigDecimal v) { this.frustrationScore = v; }

    public BigDecimal getEffortScore() { return effortScore; }
    public void setEffortScore(BigDecimal v) { this.effortScore = v; }

    public BigDecimal getTrustRiskScore() { return trustRiskScore; }
    public void setTrustRiskScore(BigDecimal v) { this.trustRiskScore = v; }

    public BigDecimal getLoopScore() { return loopScore; }
    public void setLoopScore(BigDecimal v) { this.loopScore = v; }

    public Integer getRepetitionCount() { return repetitionCount; }
    public void setRepetitionCount(Integer v) { this.repetitionCount = v; }

    public String getSuggestedMode() { return suggestedMode; }
    public void setSuggestedMode(String v) { this.suggestedMode = v; }

    public String getSelectedMode() { return selectedMode; }
    public void setSelectedMode(String v) { this.selectedMode = v; }

    public String getRationaleJson() { return rationaleJson; }
    public void setRationaleJson(String v) { this.rationaleJson = v; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
