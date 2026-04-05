import Link from "next/link";

export function DemoStatusBar({ conversationId }: { conversationId: string | null }) {
  const shortId = conversationId ? conversationId.slice(0, 8) : "...";

  return (
    <header className="bg-white border-b border-gray-200 px-4 py-3 flex items-center justify-between">
      <div>
        <h1 className="text-sm font-semibold text-gray-900">Support Chat</h1>
        <p className="text-xs text-gray-500 font-mono">Case #{shortId}</p>
      </div>
      {conversationId && (
        <Link
          href={`/cases/${conversationId}`}
          target="_blank"
          className="text-[10px] text-gray-400 hover:text-gray-600 font-mono transition-colors"
        >
          View admin console &rarr;
        </Link>
      )}
    </header>
  );
}
