package Services;

import Models.LeaveRequests;
import Repository.EmployeeRepository;
import Repository.LeaveRepository;
import Abstract.EmployeeBase;

import java.time.LocalDate;

public class LeaveService {
    private LeaveRepository leaveRepo;
    private EmployeeRepository repo;

    public LeaveService(LeaveRepository leaveRepo, EmployeeRepository repo) {
        this.leaveRepo = leaveRepo;
        this.repo = repo;
    }

    public int GetEmployeeID(int EmID) {
        EmployeeBase emp = repo.GetByID(EmID);
        if (emp == null) {
            System.out.println(" Employee not found!");
            return -1;
        }
        return emp.getEmployeeID();
    }

    public void SubmitLeaveRequest(int employeeID, LocalDate startDate, LocalDate endDate, String reason, String status) {
        LeaveRequests leave = new LeaveRequests();
        leave.setLeaveID(leaveRepo.GenerateLeaveID());
        leave.setEmployeeID(employeeID);
        leave.setStartDate(startDate);
        leave.setEndDate(endDate);
        leave.setReason(reason);
        leave.setStatus(status);

        leaveRepo.AddLeaveRequest(leave);
    }
}
