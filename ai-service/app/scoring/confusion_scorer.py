"""
Heuristic confusion scorer based on question density and uncertainty language.
"""

from app.core.config import CONFUSION_KEYWORDS


def score_confusion(messages: list[str]) -> float:
    """
    Score confusion 0-1 from recent customer messages.
    Uses question density and uncertainty keyword presence.
    """
    if not messages:
        return 0.0

    total_score = 0.0
    text = " ".join(messages).lower()
    words = text.split()
    word_count = max(len(words), 1)

    # Question density — ratio of question marks to messages (max contribution: 0.5)
    question_count = sum(m.count("?") for m in messages)
    question_density = min(question_count / max(len(messages), 1), 1.0)
    total_score += question_density * 0.5

    # Confusion keyword hits (max contribution: 0.5)
    keyword_hits = sum(1 for kw in CONFUSION_KEYWORDS if kw in text)
    keyword_score = min(keyword_hits / 4.0, 1.0)
    total_score += keyword_score * 0.5

    return max(0.0, min(1.0, total_score))
