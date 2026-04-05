const statusStyles: Record<string, string> = {
  OPEN: "bg-[#4a7ebb]/15 text-[#4a7ebb]",
  ESCALATED: "bg-red-500/15 text-red-400",
  RESOLVED: "bg-green-500/15 text-green-400",
};

const modeStyles: Record<string, string> = {
  DIRECT_ANSWER: "bg-green-500/15 text-green-400",
  CLARIFICATION_REQUIRED: "bg-amber-500/15 text-amber-400",
  HUMAN_REVIEW_DRAFT: "bg-amber-500/15 text-amber-400",
  HUMAN_ESCALATION: "bg-red-500/15 text-red-400",
  SAFE_REFUSAL: "bg-[#5a5a6a]/15 text-[#8a8a96]",
};

export function CaseStatusBadge({ status }: { status: string }) {
  const style = statusStyles[status] || "bg-[#2e2e38] text-[#8a8a96]";
  return (
    <span className={`inline-flex items-center px-2 py-0.5 rounded text-[10px] font-medium font-[family-name:var(--font-geist-mono)] uppercase tracking-wider ${style}`}>
      {status}
    </span>
  );
}

export function ResolutionModeBadge({ mode }: { mode: string | null }) {
  if (!mode) return null;
  const label = mode.replace(/_/g, " ");
  const style = modeStyles[mode] || "bg-[#2e2e38] text-[#8a8a96]";
  return (
    <span className={`inline-flex items-center px-2 py-0.5 rounded text-[10px] font-medium font-[family-name:var(--font-geist-mono)] tracking-wider ${style}`}>
      {label}
    </span>
  );
}
