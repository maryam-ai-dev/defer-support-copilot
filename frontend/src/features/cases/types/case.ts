export interface CaseListItem {
  caseFileId: string;
  conversationId: string;
  status: string;
  resolutionMode: string | null;
  issueSummary: string | null;
  currentFrustrationScore: number;
  currentEffortScore: number;
  escalationCandidate: boolean;
  updatedAt: string;
}

export type CaseFilter = "all" | "escalated" | "high-effort" | "unresolved";
