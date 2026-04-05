import { WorkspaceSidebar } from "./WorkspaceSidebar";
import { WorkspaceCenterPanel } from "./WorkspaceCenterPanel";
import { WorkspaceRightPanel } from "./WorkspaceRightPanel";

export function WorkspaceShell({
  center,
  right,
}: {
  center: React.ReactNode;
  right: React.ReactNode;
}) {
  return (
    <div className="flex h-full overflow-hidden">
      <WorkspaceSidebar />
      <WorkspaceCenterPanel>{center}</WorkspaceCenterPanel>
      <WorkspaceRightPanel>{right}</WorkspaceRightPanel>
    </div>
  );
}
