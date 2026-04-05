"""
Heuristic frustration scorer based on keyword density, escalation phrases, and tone signals.
"""

from app.core.config import FRUSTRATION_KEYWORDS, FRUSTRATION_ESCALATION_PHRASES


def score_frustration(messages: list[str]) -> float:
    """
    Score frustration 0-1 from recent customer messages.
    Uses keyword hits, escalation phrases, uppercase ratio, and exclamation density.
    """
    if not messages:
        return 0.0

    total_score = 0.0
    text = " ".join(messages).lower()
    words = text.split()
    word_count = max(len(words), 1)

    # Keyword density (max contribution: 0.4)
    keyword_hits = sum(1 for w in words if w.strip(".,!?") in FRUSTRATION_KEYWORDS)
    keyword_density = min(keyword_hits / word_count * 10, 1.0)
    total_score += keyword_density * 0.4

    # Escalation phrase matches (max contribution: 0.3)
    phrase_hits = sum(1 for phrase in FRUSTRATION_ESCALATION_PHRASES if phrase in text)
    phrase_score = min(phrase_hits / 3.0, 1.0)
    total_score += phrase_score * 0.3

    # Uppercase ratio — shouting indicator (max contribution: 0.15)
    raw_text = " ".join(messages)
    alpha_chars = [c for c in raw_text if c.isalpha()]
    if alpha_chars:
        upper_ratio = sum(1 for c in alpha_chars if c.isupper()) / len(alpha_chars)
        total_score += min(upper_ratio * 2, 1.0) * 0.15

    # Exclamation density (max contribution: 0.15)
    exclamation_count = raw_text.count("!")
    excl_density = min(exclamation_count / max(len(messages), 1) / 2.0, 1.0)
    total_score += excl_density * 0.15

    return max(0.0, min(1.0, total_score))
