package Command;

import Abstract.LeaveRequestBase;

public class SubmitLeaveCommand implements ICommand {
    private LeaveRequestBase leaveRequestBase;
    private int empID;

    public SubmitLeaveCommand(LeaveRequestBase leaveRequestBase, int empID) {
        this.leaveRequestBase = leaveRequestBase;
        this.empID = empID;
    }

    @Override
    public void Execute() {
        leaveRequestBase.SubmitRequest(empID);
    }
}
