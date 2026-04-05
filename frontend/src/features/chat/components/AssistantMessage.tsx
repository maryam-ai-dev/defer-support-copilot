import { WorkspaceMessage } from "@/features/workspace/types/workspace";
import { CitationChips } from "./CitationChips";

export function AssistantMessage({
  message,
  citations,
}: {
  message: WorkspaceMessage;
  citations?: string[];
}) {
  return (
    <div className="flex justify-start">
      <div className="max-w-[80%]">
        <div className="rounded-lg rounded-bl-sm px-3.5 py-2.5 bg-[#22222a] border border-[#2e2e38] text-[#e8e8f0] text-sm leading-relaxed">
          {message.body}
        </div>
        <CitationChips citations={citations} />
        <p className="text-[10px] text-[#5a5a6a] mt-1 font-[family-name:var(--font-geist-mono)]">
          Assistant
        </p>
      </div>
    </div>
  );
}
