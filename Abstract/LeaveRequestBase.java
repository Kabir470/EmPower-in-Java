package Abstract;

import Repository.LeaveRepository;
import Services.LeaveService;
import Models.LeaveRequests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LeaveRequestBase {
    private LeaveService leaveService;
    private LeaveRepository leaveRepo;
    private Scanner scanner;

    public LeaveRequestBase(LeaveService leaveService, LeaveRepository leaveRepo) {
        this.leaveService = leaveService;
        this.leaveRepo = leaveRepo;
        this.scanner = new Scanner(System.in);
    }

    public void SubmitRequest(int employeeID) {
        int empId = employeeID;
        System.out.println("Employee ID: " + empId);
        System.out.print("Start Date (yyyy-MM-dd): ");
        LocalDate startDate = LocalDate.parse("2025-02-03");
        System.out.print("\nEnd Date (yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse("2025-02-10");
        System.out.print("\nReason for leave: ");
        String reason = scanner.nextLine();
        System.out.print("\nStatus (Pending/Approved/Rejected): ");
        String status = "Pending";
        leaveService.SubmitLeaveRequest(empId, startDate, endDate, reason, status);
    }

    public void UpdateLeaveStatus() {
        System.out.print("Enter Leave ID to update: ");
        int leaveId = Integer.parseInt(scanner.nextLine());
        LeaveRequests leave = leaveRepo.GetLeaveByID(leaveId);
        printLeaveDetails(leave);

        int choice;
        String status = "";
        while (true) {
            System.out.print("Enter choice (1 for Pending, 2 for Approved, 3 for Rejected): ");
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 3) {
                    if (choice == 1) status = "Pending";
                    else if (choice == 2) status = "Approved";
                    else status = "Rejected";
                    break;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input! Please enter a number between 1 and 3.");
        }
        String newStatus = status;
        leaveRepo.UpdateLeaveStatus(leaveId, newStatus);
        printLeaveDetails(leave);
    }

    public void printLeaveDetails(LeaveRequests leave) {
        if (leave == null) return;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("---------Leave Request Details:-------\n");
        System.out.println("Leave ID: " + leave.getLeaveID());
        System.out.println("Employee ID: " + leave.getEmployeeID());
        System.out.println("Start Date: " + (leave.getStartDate() != null ? leave.getStartDate().format(dtf) : "N/A"));
        System.out.println("End Date: " + (leave.getEndDate() != null ? leave.getEndDate().format(dtf) : "N/A"));
        System.out.println("Reason: " + leave.getReason());
        System.out.println("Status: " + leave.getStatus());
        System.out.println("\n---------------------------------------");
    }

    public void ViewLeaveDetails(int employeeID) {
        List<LeaveRequests> leaves = leaveRepo.GetLeaveByEmployeeID(employeeID);

        if (leaves == null || leaves.isEmpty()) {
            System.out.println(" No leave requests found!");
            scanner.nextLine();
            return;
        }

        System.out.println("\nFound " + leaves.size() + " leave request(s):");

        for (LeaveRequests l : leaves) {
            printLeaveDetails(l);
        }

        System.out.println("Press enter to return.");
        scanner.nextLine();
    }
}
