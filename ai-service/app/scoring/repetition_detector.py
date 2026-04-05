"""
Detects repetition by comparing the current message embedding to prior messages.
"""

from sentence_transformers import SentenceTransformer

from app.core.config import REPETITION_SIMILARITY_THRESHOLD
from app.retrieval.vector_retriever import _get_model


def detect_repetition(
    current_message: str,
    prior_messages: list[str],
) -> tuple[bool, float]:
    """
    Compare current message to prior messages via cosine similarity.
    Returns (is_repeated, max_similarity_score).
    """
    if not prior_messages or not current_message.strip():
        return False, 0.0

    model = _get_model()
    current_emb = model.encode(current_message)
    prior_embs = model.encode(prior_messages)

    # Compute cosine similarities
    from numpy import dot
    from numpy.linalg import norm

    max_sim = 0.0
    for prior_emb in prior_embs:
        sim = float(dot(current_emb, prior_emb) / (norm(current_emb) * norm(prior_emb)))
        if sim > max_sim:
            max_sim = sim

    is_repeated = max_sim >= REPETITION_SIMILARITY_THRESHOLD
    return is_repeated, max_sim
