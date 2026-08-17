package MenuUI;

import Abstract.LeaveRequestBase;
import Command.ICommand;
import Command.SubmitLeaveCommand;
import Command.ViewProfileCommand;
import Services.EmployeeProfileServices;

import java.util.Scanner;

public class EmployeeMenuHandler {
    private EmployeeProfileServices employeeProfileServices;
    private LeaveRequestBase leaveRequestBase;
    private Scanner scanner;

    public EmployeeMenuHandler(EmployeeProfileServices profileServices, LeaveRequestBase leaveRequestBase) {
        this.employeeProfileServices = profileServices;
        this.leaveRequestBase = leaveRequestBase;
        this.scanner = new Scanner(System.in);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void runEmployeeMenu(int eID) {
        while (true) {
            clearScreen();
            System.out.println("\n===== EMPLOYEE panel =====");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. List All Employees");
            System.out.println("4. Apply For Leave");
            System.out.println("5. View my Leaves");
            System.out.println("6. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            ICommand command = null;

            switch (choice) {
                case "1" -> command = new ViewProfileCommand(employeeProfileServices, eID);
                case "4" -> command = new SubmitLeaveCommand(leaveRequestBase, eID);
                case "5" -> leaveRequestBase.ViewLeaveDetails(eID);
                case "6" -> { return; }
                default -> System.out.println(" Invalid choice!");
            }

            if (command != null) {
                command.Execute();
            }
        }
    }
}
