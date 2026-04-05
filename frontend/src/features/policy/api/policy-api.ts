const API_BASE = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080";

export interface PolicyOverride {
  escalationFrustrationThreshold: number | null;
  escalationEffortThreshold: number | null;
  escalationRepetitionCount: number | null;
  minConfidenceForDirectAnswer: number | null;
  requiresReviewConfidenceFloor: number | null;
}

export interface TurnComparison {
  turnIndex: number;
  suggestedMode: string;
  selectedMode: string;
  simulatedMode: string;
  changed: boolean;
}

export interface SimulationResult {
  turns: TurnComparison[];
}

export async function simulatePolicy(
  conversationId: string,
  policyOverride: PolicyOverride
): Promise<SimulationResult> {
  const res = await fetch(`${API_BASE}/api/v1/policies/simulate`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ conversationId, policyOverride }),
  });
  if (!res.ok) throw new Error(`Simulation failed: ${res.status}`);
  return res.json();
}
