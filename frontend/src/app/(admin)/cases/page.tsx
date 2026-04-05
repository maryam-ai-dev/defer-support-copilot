"use client";

import { CaseList } from "@/features/cases/components/CaseList";

export default function CasesPage() {
  return (
    <div className="flex h-full">
      <div className="w-[280px] shrink-0 bg-[#1a1a1f] border-r border-[#2e2e38]">
        <CaseList />
      </div>
      <div className="flex-1 flex items-center justify-center">
        <div className="text-center">
          <h2 className="text-[#e8e8f0] text-lg font-semibold mb-2">Cases</h2>
          <p className="text-[#5a5a6a] text-sm">Select a case to view the workspace.</p>
        </div>
      </div>
    </div>
  );
}
