package com.defer.backend.casefile.domain;

public enum ResolutionMode {
    DIRECT_ANSWER,
    CLARIFICATION_REQUIRED,
    HUMAN_REVIEW_DRAFT,
    HUMAN_ESCALATION,
    SAFE_REFUSAL
}
