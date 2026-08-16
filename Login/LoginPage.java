package Login;

import Abstract.EmployeeBase;
import MenuUI.AdminMenuHandler;
import MenuUI.EmployeeMenuHandler;
import Repository.EmployeeRepository;

import java.util.Scanner;

public class LoginPage {
    private int enterID;
    private AdminMenuHandler adminMenu;
    private EmployeeMenuHandler employeeMenu;
    private EmployeeRepository repo;
    private Scanner scanner;

    public LoginPage(AdminMenuHandler adminMenu, EmployeeMenuHandler employeeMenu, EmployeeRepository repo) {
        this.adminMenu = adminMenu;
        this.employeeMenu = employeeMenu;
        this.repo = repo;
        this.scanner = new Scanner(System.in);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void runLoginPage() {
        clearScreen();
        while (true) {
            clearScreen();
            System.out.println("===== LOGIN PAGE =====");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> adminMenu.Run();
                case "2" -> checkCase2();
                case "3" -> { return; }
                default -> System.out.println(" Invalid choice!");
            }
        }
    }

    public void checkCase2() {
        clearScreen();
        System.out.print("enter ur employee id: ");
        try {
            enterID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Invalid ID format!");
            return;
        }

        EmployeeBase emp = repo.GetByID(enterID);
        if (emp == null) {
            System.out.println(" Employee not found!enter again...");
            scanner.nextLine();
            return;
        }

        System.out.println("Employee found: " + emp.getName());
        System.out.println("Position: " + emp.getPosition());

        System.out.print("please enter to continue...");
        scanner.nextLine();

        employeeMenu.runEmployeeMenu(enterID);
    }
}
