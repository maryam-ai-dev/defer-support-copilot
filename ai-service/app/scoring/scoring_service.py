"""
Orchestrates all scorers and returns SupportStateScores.
"""

from pydantic import BaseModel

from app.scoring.frustration_scorer import score_frustration
from app.scoring.confusion_scorer import score_confusion
from app.scoring.effort_estimator import estimate_effort
from app.scoring.trust_risk_scorer import score_trust_risk
from app.scoring.repetition_detector import detect_repetition


class SupportStateScores(BaseModel):
    frustration_score: float
    confusion_score: float
    effort_score: float
    trust_risk_score: float
    is_repeated: bool
    repetition_similarity: float


def score_support_state(
    current_message: str,
    customer_messages: list[str],
    message_count: int,
    repetition_count: int = 0,
) -> SupportStateScores:
    """
    Run all scorers on the current conversation state.

    Args:
        current_message: Latest customer message.
        customer_messages: All customer messages in the conversation.
        message_count: Total message count (all senders).
        repetition_count: Known repetition count from CaseFile.
    """
    frustration = score_frustration(customer_messages)
    confusion = score_confusion(customer_messages)
    effort = estimate_effort(message_count, repetition_count)
    trust_risk = score_trust_risk(frustration, effort, customer_messages)

    # Prior messages = all customer messages except the current one
    prior = customer_messages[:-1] if len(customer_messages) > 1 else []
    is_repeated, rep_sim = detect_repetition(current_message, prior)

    return SupportStateScores(
        frustration_score=round(frustration, 3),
        confusion_score=round(confusion, 3),
        effort_score=round(effort, 3),
        trust_risk_score=round(trust_risk, 3),
        is_repeated=is_repeated,
        repetition_similarity=round(rep_sim, 3),
    )
