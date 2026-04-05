"use client";

import { useEffect, useState, useRef, useCallback } from "react";
import { useRouter } from "next/navigation";
import { startConversation, sendTurn } from "@/features/chat/api/conversation-api";
import { TurnResponse } from "@/features/chat/types/message";

interface DemoMessage {
  id: string;
  role: "customer" | "assistant" | "system";
  text: string;
}

export default function DemoPage() {
  const router = useRouter();
  const [conversationId, setConversationId] = useState<string | null>(null);
  const [messages, setMessages] = useState<DemoMessage[]>([]);
  const [input, setInput] = useState("");
  const [sending, setSending] = useState(false);
  const bottomRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    startConversation("demo").then((conv) => {
      setConversationId(conv.id);
    });
  }, []);

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages.length]);

  const handleSend = useCallback(async (text: string) => {
    if (!conversationId || sending || !text.trim()) return;
    setSending(true);

    const customerMsg: DemoMessage = {
      id: crypto.randomUUID(),
      role: "customer",
      text: text.trim(),
    };
    setMessages((prev) => [...prev, customerMsg]);
    setInput("");

    try {
      const result: TurnResponse = await sendTurn(conversationId, text.trim());

      const assistantMsg: DemoMessage = {
        id: result.assistantMessage.id,
        role: "assistant",
        text: result.assistantMessage.body,
      };
      setMessages((prev) => [...prev, assistantMsg]);

      if (result.escalated) {
        setMessages((prev) => [
          ...prev,
          {
            id: crypto.randomUUID(),
            role: "system",
            text: "Connecting you to a specialist. A support agent will review your case shortly.",
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
    <div className="min-h-screen bg-[#f5f5f7] flex flex-col">
      {/* Header */}
      <header className="bg-white border-b border-gray-200 px-4 py-3 flex items-center justify-between">
        <div>
          <h1 className="text-sm font-semibold text-gray-900">Support</h1>
          <p className="text-xs text-gray-500">We&apos;re here to help</p>
        </div>
        {conversationId && (
          <button
            onClick={() => router.push(`/cases/${conversationId}`)}
            className="text-[10px] text-gray-400 hover:text-gray-600 font-mono transition-colors"
          >
            Admin view &rarr;
          </button>
        )}
      </header>

      {/* Messages */}
      <div className="flex-1 overflow-y-auto">
        <div className="max-w-[680px] mx-auto px-4 py-6 space-y-3">
          {messages.length === 0 && (
            <div className="text-center py-16">
              <p className="text-gray-400 text-sm">How can we help you today?</p>
            </div>
          )}
          {messages.map((msg) => {
            if (msg.role === "customer") {
              return (
                <div key={msg.id} className="flex justify-end">
                  <div className="max-w-[75%] rounded-2xl rounded-br-md px-4 py-2.5 bg-blue-500 text-white text-sm leading-relaxed">
                    {msg.text}
                  </div>
                </div>
              );
            }
            if (msg.role === "assistant") {
              return (
                <div key={msg.id} className="flex justify-start">
                  <div className="max-w-[80%] rounded-2xl rounded-bl-md px-4 py-2.5 bg-white border border-gray-200 text-gray-800 text-sm leading-relaxed shadow-sm">
                    {msg.text}
                  </div>
                </div>
              );
            }
            return (
              <div key={msg.id} className="flex justify-center">
                <div className="px-3 py-1.5 rounded-full bg-gray-100 text-xs text-gray-500">
                  {msg.text}
                </div>
              </div>
            );
          })}
          {sending && (
            <div className="flex justify-start">
              <div className="px-4 py-2.5 rounded-2xl bg-white border border-gray-200 shadow-sm">
                <div className="flex gap-1">
                  <div className="w-1.5 h-1.5 rounded-full bg-gray-300 animate-bounce" style={{ animationDelay: "0ms" }} />
                  <div className="w-1.5 h-1.5 rounded-full bg-gray-300 animate-bounce" style={{ animationDelay: "150ms" }} />
                  <div className="w-1.5 h-1.5 rounded-full bg-gray-300 animate-bounce" style={{ animationDelay: "300ms" }} />
                </div>
              </div>
            </div>
          )}
          <div ref={bottomRef} />
        </div>
      </div>

      {/* Composer */}
      <div className="bg-white border-t border-gray-200">
        <div className="max-w-[680px] mx-auto px-4 py-3">
          <div className="flex gap-2">
            <input
              type="text"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyDown={(e) => {
                if (e.key === "Enter" && !e.shiftKey) {
                  e.preventDefault();
                  handleSend(input);
                }
              }}
              disabled={sending || !conversationId}
              placeholder="Type your message..."
              className="flex-1 bg-gray-50 border border-gray-200 rounded-full px-4 py-2.5 text-sm text-gray-800 placeholder-gray-400 focus:outline-none focus:border-blue-300 disabled:opacity-50"
            />
            <button
              onClick={() => handleSend(input)}
              disabled={sending || !input.trim() || !conversationId}
              className="px-5 py-2.5 rounded-full bg-blue-500 text-white text-sm font-medium hover:bg-blue-600 transition-colors disabled:opacity-40 disabled:cursor-not-allowed"
            >
              Send
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
