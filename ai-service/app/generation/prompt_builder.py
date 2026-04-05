"""
Builds system and user prompts for the answer generator using
latest message, recent history, case summary, and evidence bundle.
"""

from app.grounding.evidence_bundle import EvidenceBundle


def build_system_prompt() -> str:
    return (
        "You are a helpful, professional support agent. "
        "Answer the customer's question using ONLY the provided evidence from the knowledge base. "
        "If the evidence does not fully answer the question, say so honestly rather than guessing. "
        "Be concise, empathetic, and action-oriented. "
        "Do not invent policies, timelines, or processes not supported by the evidence. "
        "Reference evidence naturally — do not use citation labels in your response."
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
