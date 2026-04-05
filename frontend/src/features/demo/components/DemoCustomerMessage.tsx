export function DemoCustomerMessage({ text }: { text: string }) {
  return (
    <div className="flex justify-end">
      <div className="max-w-[75%] rounded-2xl rounded-br-md px-4 py-2.5 bg-blue-500 text-white text-sm leading-relaxed">
        {text}
      </div>
    </div>
  );
}
