"""
Generates a short, non-repetitive escalation message for the customer.
"""

import os
from openai import OpenAI

_client: OpenAI | None = None


def _get_client() -> OpenAI:
    global _client
    if _client is None:
        _client = OpenAI(api_key=os.getenv("OPENAI_API_KEY", ""))
    return _client


def generate_escalation_response(
    latest_message: str,
    case_summary: str | None = None,
) -> str:
    """Generate a brief, empathetic escalation acknowledgment message."""
    client = _get_client()

    system = (
        "You are a support agent escalating a case to a specialist. "
        "Write a short (2-3 sentences), empathetic message that: "
        "1) Acknowledges the customer's frustration without being repetitive. "
        "2) Confirms the case is being escalated to a specialist. "
        "3) Sets a clear expectation for next steps. "
        "Do not repeat what the customer said back to them verbatim."
    )

    user_parts = []
    if case_summary:
        user_parts.append(f"Case context: {case_summary}")
    user_parts.append(f"Customer message: {latest_message}")

    response = client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[
            {"role": "system", "content": system},
            {"role": "user", "content": "\n\n".join(user_parts)},
        ],
        max_tokens=150,
        temperature=0.5,
    )

    return response.choices[0].message.content.strip()
