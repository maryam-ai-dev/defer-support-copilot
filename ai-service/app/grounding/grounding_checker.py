"""
Checks whether retrieval confidence meets the grounding threshold.
"""

DEFAULT_CONFIDENCE_THRESHOLD = 0.45


def check_grounding(
    retrieval_confidence: float,
    threshold: float = DEFAULT_CONFIDENCE_THRESHOLD,
) -> tuple[bool, bool]:
    """
    Returns (is_grounded, insufficiency_flag).

    is_grounded: True if confidence >= threshold
    insufficiency_flag: True if confidence is below threshold (evidence insufficient)
    """
    is_grounded = retrieval_confidence >= threshold
    insufficiency_flag = not is_grounded
    return is_grounded, insufficiency_flag
