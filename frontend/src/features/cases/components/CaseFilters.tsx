"use client";

import { CaseFilter } from "../types/case";

const filters: { key: CaseFilter; label: string }[] = [
  { key: "all", label: "All" },
  { key: "escalated", label: "Escalated" },
  { key: "high-effort", label: "High Effort" },
  { key: "unresolved", label: "Unresolved" },
];

export function CaseFilters({
  active,
  onChange,
}: {
  active: CaseFilter;
  onChange: (filter: CaseFilter) => void;
}) {
  return (
    <div className="flex gap-1.5 px-3 py-2">
      {filters.map((f) => (
        <button
          key={f.key}
          onClick={() => onChange(f.key)}
          className={`px-2.5 py-1 rounded text-xs transition-colors ${
            active === f.key
              ? "bg-[#4a7ebb]/20 text-[#4a7ebb]"
              : "text-[#5a5a6a] hover:text-[#8a8a96] hover:bg-[#22222a]"
          }`}
        >
          {f.label}
        </button>
      ))}
    </div>
  );
}
