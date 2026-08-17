package Observer;

import Interfaces.INotificationService;
import Models.LeaveRequests;

public class HRLeaveObserver implements ILeaveObserver, INotificationService {
    @Override
    public void OnLeaveStatusChanged(LeaveRequests leave) {
        if (leave != null) {
            SendNotification("Leave ID #" + leave.getLeaveID() + " status changed to " + leave.getStatus(), leave.getEmployeeID());
        }
    }

    @Override
    public void SendNotification(String message, int iemployeeID) {
        System.out.println("[NOTIFICATION for Employee ID " + iemployeeID + "]: " + message);
    }
}
