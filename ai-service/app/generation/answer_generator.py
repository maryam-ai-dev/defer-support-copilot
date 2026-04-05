"""
Calls OpenAI to generate a grounded draft reply.
"""

import os
from openai import OpenAI

from app.generation.prompt_builder import build_system_prompt, build_user_prompt
from app.grounding.evidence_bundle import EvidenceBundle

_client: OpenAI | None = None


def _get_client() -> OpenAI:
    global _client
    if _client is None:
        _client = OpenAI(api_key=os.getenv("OPENAI_API_KEY", ""))
    return _client


def generate_answer(
    latest_message: str,
    history_summary: str | None,
    case_summary: str | None,
    evidence: EvidenceBundle,
) -> str:
    """Generate a grounded support reply using evidence from the knowledge base."""
    client = _get_client()

    system = build_system_prompt()
    user = build_user_prompt(latest_message, history_summary, case_summary, evidence)

    response = client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[
            {"role": "system", "content": system},
            {"role": "user", "content": user},
        ],
        max_tokens=400,
        temperature=0.4,
    )

    return response.choices[0].message.content.strip()
