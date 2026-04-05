export function DemoCustomerMessage({ text }: { text: string }) {
  return (
    <div className="flex justify-end">
      <div className="max-w-[75%] rounded-2xl rounded-br-md px-4 py-2.5 bg-[#4a7ebb]/20 text-[#e8e8f0] text-sm leading-relaxed">
        {text}
      </div>
    </div>
  );
}
