"""
Builds citation list from selected evidence chunks.
"""

from pydantic import BaseModel
from uuid import UUID

from app.retrieval.vector_retriever import RetrievedChunk


class Citation(BaseModel):
    document_id: UUID
    chunk_id: UUID
    label: str


def build_citations(chunks: list[RetrievedChunk]) -> list[Citation]:
    """Build a citation list from selected chunks."""
    citations = []
    for i, chunk in enumerate(chunks):
        citations.append(
            Citation(
                document_id=chunk.document_id,
                chunk_id=chunk.chunk_id,
                label=f"[{i + 1}]",
            )
        )
    return citations
