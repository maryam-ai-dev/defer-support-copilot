export function WorkspaceRightPanel({ children }: { children: React.ReactNode }) {
  return (
    <div className="w-[360px] shrink-0 bg-[#22222a] border-l border-[#2e2e38] h-full overflow-y-auto">
      {children}
    </div>
  );
}
