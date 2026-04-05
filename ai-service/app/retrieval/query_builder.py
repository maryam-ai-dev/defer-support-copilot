"""
Builds a clean retrieval query from a raw user message and optional case summary.
"""


def build_retrieval_query(user_message: str, case_summary: str | None = None) -> str:
    """
    Combine user message with case context for better retrieval.
    Strips whitespace and optionally prepends context from the case summary.
    """
    message = user_message.strip()
    if not message:
        return ""

    if case_summary and case_summary.strip():
        return f"Context: {case_summary.strip()}\n\nQuery: {message}"

    return message
