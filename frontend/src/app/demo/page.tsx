"use client";

import { useEffect, useState, useCallback } from "react";
import { createDemoConversation, sendMessage } from "@/features/demo/api/demo-api";
import { TurnResponse } from "@/features/chat/types/message";
import { DemoShell } from "@/features/demo/components/DemoShell";
import { DemoStatusBar } from "@/features/demo/components/DemoStatusBar";
import { DemoChatThread, DemoMessage } from "@/features/demo/components/DemoChatThread";
import { DemoChatComposer } from "@/features/demo/components/DemoChatComposer";

export default function DemoPage() {
  const [conversationId, setConversationId] = useState<string | null>(null);
  const [messages, setMessages] = useState<DemoMessage[]>([]);
  const [sending, setSending] = useState(false);
  const [escalated, setEscalated] = useState(false);

  useEffect(() => {
    createDemoConversation().then((conv) => {
      setConversationId(conv.id);
    });
  }, []);

  const handleSend = useCallback(async (text: string) => {
    if (!conversationId || sending || !text.trim()) return;
    setSending(true);

    setMessages((prev) => [
      ...prev,
      { id: crypto.randomUUID(), role: "customer", text: text.trim() },
    ]);

    try {
      const result: TurnResponse = await sendMessage(conversationId, text.trim());

      setMessages((prev) => [
        ...prev,
        { id: result.assistantMessage.id, role: "assistant", text: result.assistantMessage.body },
      ]);

      if (result.escalated) {
        setEscalated(true);
        setMessages((prev) => [
          ...prev,
          {
            id: crypto.randomUUID(),
            role: "system",
            text: "Connecting you to a specialist. A support agent will review your case shortly.",
          },
        ]);
      }

      if (result.resolutionMode === "CLARIFICATION_REQUIRED") {
        setMessages((prev) => [
          ...prev,
          {
            id: crypto.randomUUID(),
            role: "system",
            text: "We need a bit more information to help you.",
          },
        ]);
      }
    } catch {
      setMessages((prev) => [
        ...prev,
        { id: crypto.randomUUID(), role: "system", text: "Something went wrong. Please try again." },
      ]);
    } finally {
      setSending(false);
    }
  }, [conversationId, sending]);

  return (
    <DemoShell>
      <DemoStatusBar conversationId={conversationId} />
      <DemoChatThread messages={messages} sending={sending} />
      <DemoChatComposer onSend={handleSend} disabled={sending || escalated || !conversationId} />
    </DemoShell>
  );
}
