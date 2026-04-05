import Link from "next/link";

export function DemoStatusBar({ conversationId }: { conversationId: string | null }) {
  const shortId = conversationId ? conversationId.slice(0, 8) : "...";

  return (
    <header className="bg-[#22222a] border-b border-[#2e2e38] px-4 py-3 flex items-center justify-between">
      <div className="flex items-center gap-3">
        <span className="text-[#e8e8f0] text-sm font-semibold tracking-tight font-[family-name:var(--font-geist-mono)]">
          defer
        </span>
        <span className="text-[#2e2e38]">|</span>
        <div>
          <h1 className="text-sm font-medium text-[#e8e8f0]">Support Chat</h1>
          <p className="text-[10px] text-[#5a5a6a] font-[family-name:var(--font-geist-mono)]">Case #{shortId}</p>
        </div>
      </div>
      {conversationId && (
        <Link
          href={`/cases/${conversationId}`}
          target="_blank"
          className="text-[10px] text-[#5a5a6a] hover:text-[#4a7ebb] font-[family-name:var(--font-geist-mono)] transition-colors"
        >
          View admin console &rarr;
        </Link>
      )}
    </header>
  );
}
