"""
Orchestrates query building and vector retrieval.
"""

from app.retrieval.query_builder import build_retrieval_query
from app.retrieval.vector_retriever import RetrievedChunk, retrieve_chunks


async def retrieve(
    user_message: str,
    case_summary: str | None = None,
    top_k: int = 10,
) -> list[RetrievedChunk]:
    """Build retrieval query from user message + context, then retrieve top-k chunks."""
    query = build_retrieval_query(user_message, case_summary)
    if not query:
        return []
    return await retrieve_chunks(query, top_k=top_k)
