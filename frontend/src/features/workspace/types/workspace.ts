export interface Workspace {
  conversation: {
    id: string;
    status: string;
    channel: string;
    createdAt: string;
  };
  messages: WorkspaceMessage[];
  caseFile: WorkspaceCaseFile | null;
  attemptedActions: AttemptedAction[];
  openQuestions: OpenQuestion[];
  latestCustomerState: CustomerState | null;
  latestDecision: DecisionSummary | null;
  handoffPacket: HandoffSummary | null;
}

export interface WorkspaceMessage {
  id: string;
  senderType: string;
  body: string;
  turnIndex: number;
  createdAt: string;
}

export interface WorkspaceCaseFile {
  id: string;
  status: string;
  issueSummary: string | null;
  customerGoal: string | null;
  currentResolutionMode: string | null;
  escalationCandidate: boolean;
  repetitionCount: number;
  frustrationScore: number;
  confusionScore: number;
  effortScore: number;
  trustRiskScore: number;
  updatedAt: string;
}

export interface AttemptedAction {
  id: string;
  actionType: string;
  actionSummary: string;
  outcome: string;
  source: string;
  createdAt: string;
}

export interface OpenQuestion {
  id: string;
  questionText: string;
  status: string;
  source: string;
  createdAt: string;
}

export interface CustomerState {
  frustrationScore: number;
  confusionScore: number;
  effortScore: number;
  trustRiskScore: number;
  degradationScore: number;
  createdAt: string;
}

export interface DecisionSummary {
  selectedMode: string;
  rationale: string[];
  retrievalConfidence: number;
  createdAt: string;
}

export interface HandoffSummary {
  id: string;
  escalationReason: string;
  issueSummary: string;
  suggestedNextAction: string;
  customerState: Record<string, number>;
  createdAt: string;
}
