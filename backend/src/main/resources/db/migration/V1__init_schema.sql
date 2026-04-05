-- V1: Core schema for Defer support system

CREATE TABLE conversations (
    id UUID PRIMARY KEY,
    channel VARCHAR,
    customer_id UUID,
    status VARCHAR,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE messages (
    id UUID PRIMARY KEY,
    conversation_id UUID REFERENCES conversations(id),
    sender_type VARCHAR,
    body TEXT,
    turn_index INT,
    created_at TIMESTAMP
);

CREATE TABLE case_files (
    id UUID PRIMARY KEY,
    conversation_id UUID UNIQUE REFERENCES conversations(id),
    customer_id UUID,
    status VARCHAR NOT NULL,
    issue_summary TEXT,
    customer_goal TEXT,
    current_resolution_mode VARCHAR,
    escalation_candidate BOOLEAN DEFAULT FALSE,
    current_frustration_score NUMERIC(4,3),
    current_confusion_score NUMERIC(4,3),
    current_effort_score NUMERIC(4,3),
    current_trust_risk_score NUMERIC(4,3),
    repetition_count INT DEFAULT 0,
    last_ai_action_summary TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE attempted_actions (
    id UUID PRIMARY KEY,
    case_file_id UUID REFERENCES case_files(id),
    action_type VARCHAR,
    action_summary TEXT NOT NULL,
    outcome VARCHAR,
    source VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE open_questions (
    id UUID PRIMARY KEY,
    case_file_id UUID REFERENCES case_files(id),
    question_text TEXT NOT NULL,
    status VARCHAR NOT NULL,
    source VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE customer_state_snapshots (
    id UUID PRIMARY KEY,
    case_file_id UUID REFERENCES case_files(id),
    message_id UUID,
    frustration_score NUMERIC(4,3),
    confusion_score NUMERIC(4,3),
    effort_score NUMERIC(4,3),
    trust_risk_score NUMERIC(4,3),
    degradation_score NUMERIC(4,3),
    notes TEXT,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE decision_logs (
    id UUID PRIMARY KEY,
    conversation_id UUID REFERENCES conversations(id),
    message_id UUID,
    retrieval_confidence NUMERIC(4,3),
    ambiguity_score NUMERIC(4,3),
    frustration_score NUMERIC(4,3),
    effort_score NUMERIC(4,3),
    trust_risk_score NUMERIC(4,3),
    loop_score NUMERIC(4,3),
    repetition_count INT,
    suggested_mode VARCHAR,
    selected_mode VARCHAR,
    rationale_json JSONB,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE handoff_packets (
    id UUID PRIMARY KEY,
    case_file_id UUID REFERENCES case_files(id),
    conversation_id UUID,
    escalation_reason TEXT NOT NULL,
    issue_summary TEXT NOT NULL,
    customer_goal TEXT,
    steps_attempted_json JSONB NOT NULL,
    unresolved_items_json JSONB NOT NULL,
    customer_state_json JSONB NOT NULL,
    suggested_next_action TEXT,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE knowledge_documents (
    id UUID PRIMARY KEY,
    source_type VARCHAR,
    title VARCHAR,
    version VARCHAR,
    uri TEXT,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE knowledge_chunks (
    id UUID PRIMARY KEY,
    document_id UUID REFERENCES knowledge_documents(id),
    chunk_text TEXT NOT NULL,
    metadata_json JSONB,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE trace_spans (
    id UUID PRIMARY KEY,
    conversation_id UUID,
    span_type VARCHAR,
    span_name VARCHAR,
    metadata_json JSONB,
    started_at TIMESTAMP,
    ended_at TIMESTAMP
);
