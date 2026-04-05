const eventStyles: Record<string, { border: string; text: string; label: string }> = {
  HUMAN_ESCALATION: {
    border: "border-red-500/30",
    text: "text-red-400",
    label: "Escalated to human agent",
  },
  CLARIFICATION_REQUIRED: {
    border: "border-amber-500/30",
    text: "text-amber-400",
    label: "Clarification requested",
  },
  SAFE_REFUSAL: {
    border: "border-[#5a5a6a]/30",
    text: "text-[#8a8a96]",
    label: "Out of scope — safe refusal",
  },
  HUMAN_REVIEW_DRAFT: {
    border: "border-amber-500/30",
    text: "text-amber-400",
    label: "Draft pending human review",
  },
};

export function SystemEventCard({ mode }: { mode: string }) {
  if (mode === "DIRECT_ANSWER") return null;

  const style = eventStyles[mode] || {
    border: "border-[#2e2e38]",
    text: "text-[#8a8a96]",
    label: mode,
  };

  return (
    <div className={`mx-auto max-w-[60%] border ${style.border} rounded-md px-3 py-2 text-center`}>
      <p className={`text-xs font-[family-name:var(--font-geist-mono)] ${style.text}`}>
        {style.label}
      </p>
    </div>
  );
}
