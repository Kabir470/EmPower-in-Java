package Observer;

import Models.LeaveRequests;

public interface ILeaveObserver {
    void OnLeaveStatusChanged(LeaveRequests leave);
}
