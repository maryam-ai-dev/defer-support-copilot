# Defer — Architecture

## Overview

Defer is a policy-first AI support decision system with three layers:

| Layer | Technology | Role |
|---|---|---|
| Authority | Spring Boot | Owns all business state, decisions, and persistence |
| Intelligence | FastAPI | RAG pipeline, scoring, memory extraction, eval execution |
| Frontend | Next.js | Admin console and customer demo |

## Key Principles

1. **The LLM recommends. Spring Boot decides.** FastAPI returns `suggested_mode`. Spring Boot makes the final `ResolutionMode` call after applying policy.
2. **Python does not own durable state.** Python proposes memory updates. Spring Boot applies and persists them.
3. **Policy thresholds come from the database.** Never hardcoded.

## Resolution Modes

| Mode | When |
|---|---|
| DIRECT_ANSWER | High confidence, low risk |
| CLARIFICATION_REQUIRED | Ambiguous query |
| HUMAN_REVIEW_DRAFT | Medium confidence — draft for human review |
| HUMAN_ESCALATION | High frustration/effort or policy trigger |
| SAFE_REFUSAL | Out-of-scope or sensitive topic |

## Data Flow

```
Customer message
  → Spring Boot (persist message, load CaseFile)
    → FastAPI (RAG + scoring + generation)
      → returns: suggested_mode, scores, response draft, memory updates
    → Spring Boot (apply policy, select final mode, persist everything)
  → Response to customer
```
