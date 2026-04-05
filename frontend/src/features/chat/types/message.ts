export interface TurnResponse {
  assistantMessage: {
    id: string;
    conversationId: string;
    senderType: string;
    body: string;
    turnIndex: number;
    createdAt: string;
  };
  resolutionMode: string;
  escalated: boolean;
  caseFileId: string;
  handoffId: string | null;
  traceId: string | null;
}
