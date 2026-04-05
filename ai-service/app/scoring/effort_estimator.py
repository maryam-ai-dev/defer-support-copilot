"""
Effort estimator based on message count and repeated attempts.
"""

from app.core.config import EFFORT_MSG_COUNT_CAP


def estimate_effort(message_count: int, repetition_count: int = 0) -> float:
    """
    Score effort 0-1 based on how many messages the customer has sent
    and how many times they've repeated the same issue.
    """
    # Message count contribution (max 0.6) — scales linearly up to cap
    msg_score = min(message_count / EFFORT_MSG_COUNT_CAP, 1.0) * 0.6

    # Repetition contribution (max 0.4) — each repetition adds 0.2
    rep_score = min(repetition_count * 0.2, 1.0) * 0.4

    return max(0.0, min(1.0, msg_score + rep_score))
