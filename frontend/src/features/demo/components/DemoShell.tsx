export function DemoShell({ children }: { children: React.ReactNode }) {
  return (
    <div className="min-h-screen bg-[#1a1a1f] flex flex-col">
      {children}
    </div>
  );
}
