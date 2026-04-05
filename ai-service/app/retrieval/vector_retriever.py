"""
Generates embedding for a query and retrieves top-k chunks via pgvector cosine similarity.
"""

import os
from dataclasses import dataclass
from uuid import UUID

import asyncpg
from sentence_transformers import SentenceTransformer

POSTGRES_URL = os.getenv("POSTGRES_URL", "postgresql://defer:defer@localhost:5432/defer")
MODEL_NAME = "all-MiniLM-L6-v2"

_model: SentenceTransformer | None = None


def _get_model() -> SentenceTransformer:
    global _model
    if _model is None:
        _model = SentenceTransformer(MODEL_NAME)
    return _model


@dataclass
class RetrievedChunk:
    chunk_id: UUID
    document_id: UUID
    chunk_text: str
    similarity_score: float
    metadata_json: str | None


async def retrieve_chunks(query: str, top_k: int = 10) -> list[RetrievedChunk]:
    """Embed query and retrieve top-k most similar chunks from knowledge_chunks."""
    if not query:
        return []

    model = _get_model()
    embedding = model.encode(query)
    embedding_str = "[" + ",".join(str(float(x)) for x in embedding) + "]"

    conn = await asyncpg.connect(POSTGRES_URL)
    try:
        rows = await conn.fetch(
            """
            SELECT id, document_id, chunk_text, metadata_json,
                   1 - (embedding <=> $1::vector) AS similarity
            FROM knowledge_chunks
            ORDER BY embedding <=> $1::vector
            LIMIT $2
            """,
            embedding_str,
            top_k,
        )
        return [
            RetrievedChunk(
                chunk_id=row["id"],
                document_id=row["document_id"],
                chunk_text=row["chunk_text"],
                similarity_score=float(row["similarity"]),
                metadata_json=row["metadata_json"],
            )
            for row in rows
        ]
    finally:
        await conn.close()
