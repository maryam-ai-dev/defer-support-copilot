# Defer

A policy-first AI support decision system.

**Spring Boot** (authority layer) + **FastAPI** (intelligence layer) + **Next.js** (frontend).

## Architecture

- The LLM recommends. Spring Boot decides.
- Python proposes memory updates. Spring Boot persists them.
- Policy thresholds are DB-backed, never hardcoded.

## Project Structure

```
defer/
├── backend/          # Spring Boot — authority/orchestration layer
├── ai-service/       # FastAPI — intelligence/RAG layer
├── frontend/         # Next.js — admin console + demo
├── datasets/
│   ├── support_kb/   # Knowledge base documents
│   └── eval_sets/    # Evaluation scenario JSON
├── docs/
├── scripts/
└── docker-compose.yml
```

## Running Locally

```bash
# Full stack
docker-compose up

# Individual services
cd backend && ./mvnw spring-boot:run
cd ai-service && uvicorn app.main:app --reload --port 8000
cd frontend && npm run dev
```

## Environment

Copy `.env.example` to `.env` and fill in your values.
