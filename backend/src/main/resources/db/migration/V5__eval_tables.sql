-- V5: Evaluation tables

CREATE TABLE eval_runs (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    model_version VARCHAR,
    policy_version VARCHAR,
    metrics_json JSONB,
    started_at TIMESTAMP NOT NULL,
    ended_at TIMESTAMP
);

CREATE TABLE eval_results (
    id UUID PRIMARY KEY,
    eval_run_id UUID REFERENCES eval_runs(id),
    scenario_id VARCHAR NOT NULL,
    description VARCHAR,
    expected_mode VARCHAR,
    actual_mode VARCHAR,
    passed BOOLEAN NOT NULL,
    turn_count INT,
    notes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);
