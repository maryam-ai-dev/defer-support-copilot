"""
Pydantic model for the complete evidence bundle returned by the grounding pipeline.
"""

from pydantic import BaseModel
from uuid import UUID

from app.grounding.citation_builder import Citation


class EvidenceChunk(BaseModel):
    chunk_id: UUID
    document_id: UUID
    chunk_text: str
    similarity_score: float


class EvidenceBundle(BaseModel):
    chunks: list[EvidenceChunk]
    citations: list[Citation]
    confidence: float
    is_grounded: bool
    insufficiency_flag: bool
