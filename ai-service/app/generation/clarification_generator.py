"""
Generates a targeted clarification question when the query is ambiguous.
"""

import os
from openai import OpenAI

_client: OpenAI | None = None


def _get_client() -> OpenAI:
    global _client
    if _client is None:
        _client = OpenAI(api_key=os.getenv("OPENAI_API_KEY", ""))
    return _client


def generate_clarification(
    latest_message: str,
    case_summary: str | None = None,
) -> str:
    """Generate one targeted clarification question for an ambiguous customer query."""
    client = _get_client()

    system = (
        "You are a helpful support agent. The customer's message is unclear or ambiguous. "
        "Ask ONE specific, concise clarifying question to understand their issue better. "
        "Do not apologise excessively. Be direct and helpful."
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
        max_tokens=120,
        temperature=0.4,
    )

    return response.choices[0].message.content.strip()
