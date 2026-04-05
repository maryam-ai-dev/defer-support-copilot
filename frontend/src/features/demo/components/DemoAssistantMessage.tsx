export function DemoAssistantMessage({ text }: { text: string }) {
  return (
    <div className="flex justify-start">
      <div className="max-w-[80%] rounded-2xl rounded-bl-md px-4 py-2.5 bg-[#22222a] border border-[#2e2e38] text-[#e8e8f0] text-sm leading-relaxed">
        {text}
      </div>
    </div>
  );
}
