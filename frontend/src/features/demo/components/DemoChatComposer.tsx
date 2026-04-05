"use client";

import { useState } from "react";

const quickScenarios = [
  "My order hasn't arrived",
  "I already tried that",
  "This is the third time I've contacted you",
];

export function DemoChatComposer({
  onSend,
  disabled,
}: {
  onSend: (message: string) => void;
  disabled?: boolean;
}) {
  const [input, setInput] = useState("");

  const handleSend = () => {
    const trimmed = input.trim();
    if (!trimmed || disabled) return;
    onSend(trimmed);
    setInput("");
  };

  return (
    <div className="bg-white border-t border-gray-200">
      <div className="max-w-[680px] mx-auto px-4 py-3">
        <div className="flex gap-1.5 mb-2">
          {quickScenarios.map((s) => (
            <button
              key={s}
              onClick={() => !disabled && onSend(s)}
              disabled={disabled}
              className="px-2.5 py-1 rounded-full text-[11px] text-gray-500 bg-gray-100 hover:bg-gray-200 transition-colors disabled:opacity-40"
            >
              {s}
            </button>
          ))}
        </div>
        <div className="flex gap-2">
          <input
            type="text"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter" && !e.shiftKey) {
                e.preventDefault();
                handleSend();
              }
            }}
            disabled={disabled}
            placeholder="Type your message..."
            className="flex-1 bg-gray-50 border border-gray-200 rounded-full px-4 py-2.5 text-sm text-gray-800 placeholder-gray-400 focus:outline-none focus:border-blue-300 disabled:opacity-50"
          />
          <button
            onClick={handleSend}
            disabled={disabled || !input.trim()}
            className="px-5 py-2.5 rounded-full bg-blue-500 text-white text-sm font-medium hover:bg-blue-600 transition-colors disabled:opacity-40 disabled:cursor-not-allowed"
          >
            Send
          </button>
        </div>
      </div>
    </div>
  );
}
