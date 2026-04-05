package com.defer.backend.evaluation.infrastructure;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "eval_results")
public class EvalResultEntity {

    @Id
    private UUID id;

    @Column(name = "eval_run_id")
    private UUID evalRunId;

    @Column(name = "scenario_id")
    private String scenarioId;

    private String description;

    @Column(name = "expected_mode")
    private String expectedMode;

    @Column(name = "actual_mode")
    private String actualMode;

    private boolean passed;

    @Column(name = "turn_count")
    private Integer turnCount;

    private String notes;

    @Column(name = "created_at")
    private Instant createdAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEvalRunId() { return evalRunId; }
    public void setEvalRunId(UUID evalRunId) { this.evalRunId = evalRunId; }

    public String getScenarioId() { return scenarioId; }
    public void setScenarioId(String scenarioId) { this.scenarioId = scenarioId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getExpectedMode() { return expectedMode; }
    public void setExpectedMode(String expectedMode) { this.expectedMode = expectedMode; }

    public String getActualMode() { return actualMode; }
    public void setActualMode(String actualMode) { this.actualMode = actualMode; }

    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }

    public Integer getTurnCount() { return turnCount; }
    public void setTurnCount(Integer turnCount) { this.turnCount = turnCount; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
