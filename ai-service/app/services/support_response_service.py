"""
Orchestrates the full support response pipeline:
retrieval → ranking → grounding → scoring → generation → summarisation.
"""

import logging
from pydantic import BaseModel
from uuid import UUID

from app.retrieval.retrieval_service import retrieve
from app.ranking.ranking_service import rank
from app.grounding.grounding_checker import check_grounding
from app.grounding.citation_builder import build_citations, Citation
from app.grounding.evidence_bundle import EvidenceBundle, EvidenceChunk
from app.scoring.scoring_service import score_support_state, SupportStateScores
from app.generation.answer_generator import generate_answer
from app.generation.clarification_generator import generate_clarification
from app.generation.escalation_response_generator import generate_escalation_response
from app.summarisation.issue_summary_updater import update_issue_summary
from app.summarisation.goal_extractor import extract_goal
from app.summarisation.attempted_action_extractor import extract_attempted_actions
from app.summarisation.open_question_extractor import extract_open_questions
from app.summarisation.history_compressor import compress_history

logger = logging.getLogger(__name__)


class MemoryUpdate(BaseModel):
    updated_issue_summary: str | None = None
    customer_goal: str | None = None
    attempted_actions: list[str] = []
    open_questions: list[str] = []


class DecisionSignals(BaseModel):
    loop_detected: bool = False
    repetition_detected: bool = False
    clarification_needed: bool = False


class SupportResponsePayload(BaseModel):
    suggested_mode: str
    response_draft: str
    citations: list[Citation] = []
    retrieval_confidence: float
    customer_state: SupportStateScores
    memory_update: MemoryUpdate
    decision_signals: DecisionSignals


async def build_support_response(
    latest_message_text: str,
    recent_history: list[dict],
    case_summary: str | None = None,
    customer_goal: str | None = None,
    existing_issue_summary: str | None = None,
    message_count: int = 1,
    repetition_count: int = 0,
) -> SupportResponsePayload:
    """
    Full pipeline: retrieval → ranking → grounding → scoring → generation → summarisation.
    Returns SupportResponsePayload with suggested_mode for Spring Boot to apply policy on.
    """

    # 1. Retrieve candidate chunks
    raw_chunks = await retrieve(
        user_message=latest_message_text,
        case_summary=existing_issue_summary,
    )

    # 2. Rank and select evidence
    ranking_result = rank(raw_chunks)

    # 3. Check grounding
    is_grounded, insufficiency_flag = check_grounding(ranking_result.confidence)

    # 4. Build evidence bundle
    evidence_chunks = [
        EvidenceChunk(
            chunk_id=c.chunk_id,
            document_id=c.document_id,
            chunk_text=c.chunk_text,
            similarity_score=c.similarity_score,
        )
        for c in ranking_result.selected_chunks
    ]
    citations = build_citations(ranking_result.selected_chunks)

    evidence = EvidenceBundle(
        chunks=evidence_chunks,
        citations=citations,
        confidence=ranking_result.confidence,
        is_grounded=is_grounded,
        insufficiency_flag=insufficiency_flag,
    )

    # 5. Score support state
    customer_messages = [
        m["text"] for m in recent_history if m.get("role") == "CUSTOMER"
    ]
    if latest_message_text not in customer_messages:
        customer_messages.append(latest_message_text)

    scores = score_support_state(
        current_message=latest_message_text,
        customer_messages=customer_messages,
        message_count=message_count,
        repetition_count=repetition_count,
    )

    # 6. Determine suggested mode and generate response
    suggested_mode, response_draft = _pick_mode_and_generate(
        latest_message_text=latest_message_text,
        evidence=evidence,
        scores=scores,
        case_summary=existing_issue_summary,
        history_summary=compress_history(
            [{"sender_type": m.get("role", ""), "body": m.get("text", "")} for m in recent_history]
        ),
    )

    # 7. Build memory update
    memory_update = _build_memory_update(
        latest_message_text=latest_message_text,
        customer_messages=customer_messages,
        existing_issue_summary=existing_issue_summary,
    )

    # 8. Build decision signals
    decision_signals = DecisionSignals(
        loop_detected=scores.is_repeated and repetition_count >= 2,
        repetition_detected=scores.is_repeated,
        clarification_needed=suggested_mode == "CLARIFICATION_REQUIRED",
    )

    return SupportResponsePayload(
        suggested_mode=suggested_mode,
        response_draft=response_draft,
        citations=citations,
        retrieval_confidence=ranking_result.confidence,
        customer_state=scores,
        memory_update=memory_update,
        decision_signals=decision_signals,
    )


def _pick_mode_and_generate(
    latest_message_text: str,
    evidence: EvidenceBundle,
    scores: SupportStateScores,
    case_summary: str | None,
    history_summary: str | None,
) -> tuple[str, str]:
    """Pick suggested_mode and generate the appropriate response draft."""

    # High frustration or effort → suggest escalation
    if scores.frustration_score >= 0.7 or scores.effort_score >= 0.7:
        draft = generate_escalation_response(latest_message_text, case_summary)
        return "HUMAN_ESCALATION", draft

    # Low confidence → clarification
    if evidence.confidence < 0.3:
        draft = generate_clarification(latest_message_text, case_summary)
        return "CLARIFICATION_REQUIRED", draft

    # Medium confidence → human review draft
    if not evidence.is_grounded:
        draft = generate_answer(latest_message_text, history_summary, case_summary, evidence)
        return "HUMAN_REVIEW_DRAFT", draft

    # Grounded with good confidence → direct answer
    draft = generate_answer(latest_message_text, history_summary, case_summary, evidence)
    return "DIRECT_ANSWER", draft


def _build_memory_update(
    latest_message_text: str,
    customer_messages: list[str],
    existing_issue_summary: str | None,
) -> MemoryUpdate:
    """Build memory update from summarisation modules."""
    try:
        updated_summary = update_issue_summary(latest_message_text, existing_issue_summary)
    except Exception:
        logger.warning("Failed to update issue summary via LLM, falling back")
        updated_summary = existing_issue_summary

    try:
        goal = extract_goal(customer_messages)
    except Exception:
        logger.warning("Failed to extract goal via LLM, falling back")
        goal = None

    attempted = extract_attempted_actions(latest_message_text)
    questions = extract_open_questions(customer_messages)

    return MemoryUpdate(
        updated_issue_summary=updated_summary,
        customer_goal=goal,
        attempted_actions=attempted,
        open_questions=questions,
    )
