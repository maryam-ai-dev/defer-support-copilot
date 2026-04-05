import { TurnResponse } from "@/features/chat/types/message";

const API_BASE = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080";

export async function createDemoConversation(): Promise<{ id: string }> {
  const customerId = crypto.randomUUID();
  const res = await fetch(`${API_BASE}/api/v1/conversations`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ channel: "demo", customerId }),
  });
  if (!res.ok) throw new Error(`Failed to create conversation: ${res.status}`);
  return res.json();
}

export async function sendMessage(
  conversationId: string,
  text: string
): Promise<TurnResponse> {
  const res = await fetch(
    `${API_BASE}/api/v1/conversations/${conversationId}/turn`,
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ message: text }),
    }
  );
  if (!res.ok) throw new Error(`Turn failed: ${res.status}`);
  return res.json();
}
