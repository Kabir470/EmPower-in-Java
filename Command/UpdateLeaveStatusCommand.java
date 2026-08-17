package Command;

import Abstract.LeaveRequestBase;

public class UpdateLeaveStatusCommand implements ICommand {
    private LeaveRequestBase leaveRequestBase;

    public UpdateLeaveStatusCommand(LeaveRequestBase leaveRequestBase) {
        this.leaveRequestBase = leaveRequestBase;
    }

    @Override
    public void Execute() {
        leaveRequestBase.UpdateLeaveStatus();
    }
}
