package MenuUI;

import Abstract.HireFireBase;
import Abstract.LeaveRequestBase;
import Command.ICommand;
import Command.HireEmployeeCommand;
import Command.FireEmployeeCommand;
import Command.ListEmployeesCommand;
import Command.UpdateLeaveStatusCommand;
import Services.EmployeeService;

import java.util.Scanner;

public class AdminMenuHandler {
    private EmployeeService empService;
    private HireFireBase hirefire;
    private LeaveRequestBase leaveRequestBase;
    private Scanner scanner;

    public AdminMenuHandler(EmployeeService empService, LeaveRequestBase leaveRequestBase) {
        this.empService = empService;
        this.hirefire = new HireFireBase(empService);
        this.leaveRequestBase = leaveRequestBase;
        this.scanner = new Scanner(System.in);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void ViewProfileMenu() {
        System.out.print("Enter the ID you want to search: ");
        try {
            int inputid = Integer.parseInt(scanner.nextLine());
            empService.ViewProfile(inputid);
        } catch (NumberFormatException e) {
            System.out.println(" Invalid ID format! Please enter a number.");
        }
    }

    public void Run() {
        clearScreen();
        while (true) {
            System.out.println("\n===== ADMIN Panel =====");
            System.out.println("1. Hire Employee");
            System.out.println("2. Fire Employee");
            System.out.println("3. List All Employees");
            System.out.println("4. View Employee Profile");
            System.out.println("5. View Leave Requests");
            System.out.println("6. Request Status Update");
            System.out.println("7. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            ICommand command = null;

            switch (choice) {
                case "1" -> command = new HireEmployeeCommand(hirefire);
                case "2" -> command = new FireEmployeeCommand(hirefire);
                case "3" -> command = new ListEmployeesCommand(empService);
                case "4" -> ViewProfileMenu();
                case "5" -> EnterIdForLeaveDetails();
                case "6" -> command = new UpdateLeaveStatusCommand(leaveRequestBase);
                case "7" -> { return; }
                default -> System.out.println(" Invalid choice!");
            }

            if (command != null) {
                command.Execute();
            }
        }
    }

    public void EnterIdForLeaveDetails() {
        System.out.print("Enter the Employee ID to view leave requests: ");
        try {
            int empId = Integer.parseInt(scanner.nextLine());
            leaveRequestBase.ViewLeaveDetails(empId);
        } catch (NumberFormatException e) {
            System.out.println(" Invalid ID format! Please enter a number.");
        }
    }
}
