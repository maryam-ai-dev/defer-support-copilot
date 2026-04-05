"""
Trust risk scorer — combines frustration, effort, and negative language signals.
"""

from app.core.config import NEGATIVE_SENTIMENT_KEYWORDS


def score_trust_risk(
    frustration: float,
    effort: float,
    messages: list[str],
) -> float:
    """
    Score trust risk 0-1. High when frustration + effort are high
    and negative sentiment is present.
    """
    # Weighted combination of frustration and effort (max 0.7)
    combined = (frustration * 0.5 + effort * 0.5)
    base_score = combined * 0.7

    # Negative sentiment boost (max 0.3)
    text = " ".join(messages).lower()
    words = text.split()
    word_count = max(len(words), 1)
    neg_hits = sum(1 for w in words if w.strip(".,!?") in NEGATIVE_SENTIMENT_KEYWORDS)
    neg_density = min(neg_hits / word_count * 10, 1.0)
    base_score += neg_density * 0.3

    return max(0.0, min(1.0, base_score))
