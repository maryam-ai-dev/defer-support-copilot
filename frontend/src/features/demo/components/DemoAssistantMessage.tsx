export function DemoAssistantMessage({ text }: { text: string }) {
  return (
    <div className="flex justify-start">
      <div className="max-w-[80%] rounded-2xl rounded-bl-md px-4 py-2.5 bg-white border border-gray-200 text-gray-800 text-sm leading-relaxed shadow-sm">
        {text}
      </div>
    </div>
  );
}
