import { Workspace } from "../types/workspace";

const API_BASE = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080";

export async function fetchWorkspace(conversationId: string): Promise<Workspace> {
  const res = await fetch(`${API_BASE}/api/v1/workspaces/${conversationId}`, {
    cache: "no-store",
  });
  if (!res.ok) throw new Error(`Failed to fetch workspace: ${res.status}`);
  return res.json();
}
