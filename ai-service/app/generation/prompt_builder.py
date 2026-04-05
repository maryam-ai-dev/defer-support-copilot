"""
Builds system and user prompts for the answer generator using
latest message, recent history, case summary, and evidence bundle.
"""

from app.grounding.evidence_bundle import EvidenceBundle


def build_system_prompt() -> str:
    return (
        "You are a concise, human support assistant for Defer.\n\n"
        "Rules:\n"
        "- Maximum 2-3 sentences per response. Never exceed this.\n"
        "- Never use bullet points or numbered lists in responses.\n"
        "- Be conversational and direct, not formal or corporate.\n"
        "- Ask only ONE clarifying question at a time, never multiple.\n"
        "- Lead with acknowledgement, follow with one action or question.\n"
        "- Never give a full explanation when a short answer works.\n"
        "- If you need more information, just ask for the one most important thing.\n"
        "- Answer using ONLY the provided evidence. Do not invent policies or timelines.\n"
        "- If the evidence doesn't fully answer the question, say so honestly.\n\n"
        'Example good response:\n'
        '"Sorry your order hasn\'t arrived! Can you share your order number so I can check the status?"\n\n'
        'Example bad response:\n'
        '"I\'m sorry to hear that your order hasn\'t arrived yet. First, please check the tracking information '
        'for any updates. If the tracking shows no movement..."'
    )


def build_user_prompt(
    latest_message: str,
    history_summary: str | None,
    case_summary: str | None,
    evidence: EvidenceBundle,
) -> str:
    parts: list[str] = []

    if case_summary:
        parts.append(f"Case summary: {case_summary}")

    if history_summary:
        parts.append(f"Conversation context:\n{history_summary}")

    if evidence.chunks:
        evidence_text = "\n\n".join(
            f"Evidence [{i+1}]: {chunk.chunk_text}"
            for i, chunk in enumerate(evidence.chunks)
        )
        parts.append(f"Knowledge base evidence:\n{evidence_text}")
    else:
        parts.append("No relevant knowledge base evidence was found.")

    parts.append(f"Customer message: {latest_message}")

    return "\n\n".join(parts)
