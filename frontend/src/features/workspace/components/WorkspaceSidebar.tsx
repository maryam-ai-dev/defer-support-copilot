import { CaseList } from "@/features/cases/components/CaseList";

export function WorkspaceSidebar() {
  return (
    <div className="w-[280px] shrink-0 bg-[#1a1a1f] border-r border-[#2e2e38] h-full overflow-hidden">
      <CaseList />
    </div>
  );
}
