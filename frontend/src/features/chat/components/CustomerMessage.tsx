import { WorkspaceMessage } from "@/features/workspace/types/workspace";

export function CustomerMessage({ message }: { message: WorkspaceMessage }) {
  return (
    <div className="flex justify-end">
      <div className="max-w-[75%]">
        <div className="rounded-lg rounded-br-sm px-3.5 py-2.5 bg-[#4a7ebb]/20 text-[#e8e8f0] text-sm leading-relaxed">
          {message.body}
        </div>
        <p className="text-[10px] text-[#5a5a6a] mt-1 text-right font-[family-name:var(--font-geist-mono)]">
          Customer
        </p>
      </div>
    </div>
  );
}
