export function WorkspaceCenterPanel({ children }: { children: React.ReactNode }) {
  return (
    <div className="flex-1 min-w-0 bg-[#1a1a1f] flex flex-col h-full overflow-hidden">
      {children}
    </div>
  );
}
