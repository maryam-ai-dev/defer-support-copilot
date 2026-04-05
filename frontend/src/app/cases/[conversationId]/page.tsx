"use client";

import { useEffect, useState } from "react";
import { useParams } from "next/navigation";
import { Workspace } from "@/features/workspace/types/workspace";
import { fetchWorkspace } from "@/features/workspace/api/workspace-api";
import { WorkspaceShell } from "@/features/workspace/components/WorkspaceShell";

export default function CaseWorkspacePage() {
  const params = useParams();
  const conversationId = params.conversationId as string;
  const [workspace, setWorkspace] = useState<Workspace | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!conversationId) return;
    fetchWorkspace(conversationId)
      .then(setWorkspace)
      .catch((e) => setError(e.message));
  }, [conversationId]);

  if (error) {
    return (
      <div className="flex items-center justify-center h-full">
        <p className="text-red-400 text-sm">{error}</p>
      </div>
    );
  }

  if (!workspace) {
    return (
      <div className="flex items-center justify-center h-full">
        <p className="text-[#5a5a6a] text-sm">Loading workspace...</p>
      </div>
    );
  }

  return (
    <WorkspaceShell
      center={
        <div className="flex flex-col h-full">
          <div className="border-b border-[#2e2e38] px-4 py-3">
            <h2 className="text-sm font-medium text-[#e8e8f0]">Conversation</h2>
            <p className="text-xs text-[#5a5a6a] mt-0.5 font-[family-name:var(--font-geist-mono)]">
              {workspace.conversation.id.slice(0, 8)}... &middot; {workspace.conversation.channel} &middot; {workspace.conversation.status}
            </p>
          </div>
          <div className="flex-1 overflow-y-auto px-4 py-4 space-y-3">
            {workspace.messages.map((msg) => (
              <div
                key={msg.id}
                className={`max-w-[80%] ${
                  msg.senderType === "CUSTOMER" ? "ml-auto" : "mr-auto"
                }`}
              >
                <div
                  className={`rounded-lg px-3 py-2 text-sm ${
                    msg.senderType === "CUSTOMER"
                      ? "bg-[#4a7ebb]/20 text-[#e8e8f0]"
                      : "bg-[#22222a] text-[#e8e8f0] border border-[#2e2e38]"
                  }`}
                >
                  {msg.body}
                </div>
                <p className="text-[10px] text-[#5a5a6a] mt-1 font-[family-name:var(--font-geist-mono)]">
                  {msg.senderType}
                </p>
              </div>
            ))}
          </div>
          <div className="border-t border-[#2e2e38] px-4 py-3">
            <p className="text-xs text-[#5a5a6a]">Chat composer will be added in step 10.4</p>
          </div>
        </div>
      }
      right={
        <div className="p-4 space-y-4">
          {workspace.caseFile && (
            <div>
              <h3 className="text-xs font-medium text-[#8a8a96] uppercase tracking-wider font-[family-name:var(--font-geist-mono)] mb-2">
                Case File
              </h3>
              <div className="space-y-2 text-sm">
                <div>
                  <span className="text-[#5a5a6a] text-xs">Issue</span>
                  <p className="text-[#e8e8f0]">{workspace.caseFile.issueSummary || "—"}</p>
                </div>
                <div>
                  <span className="text-[#5a5a6a] text-xs">Goal</span>
                  <p className="text-[#e8e8f0]">{workspace.caseFile.customerGoal || "—"}</p>
                </div>
                <div>
                  <span className="text-[#5a5a6a] text-xs">Mode</span>
                  <p className="text-[#e8e8f0] font-[family-name:var(--font-geist-mono)] text-xs">
                    {workspace.caseFile.currentResolutionMode || "—"}
                  </p>
                </div>
              </div>
            </div>
          )}

          {workspace.latestDecision && (
            <div>
              <h3 className="text-xs font-medium text-[#8a8a96] uppercase tracking-wider font-[family-name:var(--font-geist-mono)] mb-2">
                Latest Decision
              </h3>
              <p className="text-xs text-[#e8e8f0] font-[family-name:var(--font-geist-mono)]">
                {workspace.latestDecision.selectedMode}
              </p>
              <p className="text-xs text-[#5a5a6a] mt-1">
                Confidence: {(workspace.latestDecision.retrievalConfidence * 100).toFixed(1)}%
              </p>
              {workspace.latestDecision.rationale.map((r, i) => (
                <p key={i} className="text-xs text-[#5a5a6a] mt-0.5">{r}</p>
              ))}
            </div>
          )}

          {workspace.handoffPacket && (
            <div>
              <h3 className="text-xs font-medium text-red-400 uppercase tracking-wider font-[family-name:var(--font-geist-mono)] mb-2">
                Handoff Packet
              </h3>
              <p className="text-xs text-[#e8e8f0]">{workspace.handoffPacket.escalationReason}</p>
              <p className="text-xs text-[#5a5a6a] mt-1">{workspace.handoffPacket.suggestedNextAction}</p>
            </div>
          )}

          {!workspace.caseFile && !workspace.latestDecision && (
            <div className="text-center py-8">
              <p className="text-xs text-[#5a5a6a]">No case data yet</p>
            </div>
          )}
        </div>
      }
    />
  );
}
