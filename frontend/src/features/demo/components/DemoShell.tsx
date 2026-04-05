export function DemoShell({ children }: { children: React.ReactNode }) {
  return (
    <div className="min-h-screen bg-[#f5f5f7] flex flex-col">
      {children}
    </div>
  );
}
