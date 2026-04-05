-- V4: Recreate ivfflat index with fewer lists for small datasets
-- lists=100 requires at least 100 rows; using lists=1 for MVP

DROP INDEX IF EXISTS idx_knowledge_chunks_embedding;

CREATE INDEX idx_knowledge_chunks_embedding ON knowledge_chunks
    USING ivfflat (embedding vector_cosine_ops)
    WITH (lists = 1);
