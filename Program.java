import Abstract.CheckPassword;
import Abstract.LeaveRequestBase;
import Login.LoginPage;
import MenuUI.AdminMenuHandler;
import MenuUI.EmployeeMenuHandler;
import Repository.EmployeeRepository;
import Repository.LeaveRepository;
import Services.EmployeeProfileServices;
import Services.EmployeeService;
import Services.LeaveService;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        // Create our global repository and services ONE TIME
        EmployeeRepository repo = new EmployeeRepository();
        EmployeeService empService = new EmployeeService(repo);
        EmployeeProfileServices empProfileService = new EmployeeProfileServices(repo);
        LeaveRepository leaveRepo = new LeaveRepository();
        LeaveService leaveService = new LeaveService(leaveRepo, repo);

        // Give those single instances to our menus
        LeaveRequestBase leaveRequestBase = new LeaveRequestBase(leaveService, leaveRepo);
        AdminMenuHandler adminMenu = new AdminMenuHandler(empService, leaveRequestBase);
        EmployeeMenuHandler employeeMenu = new EmployeeMenuHandler(empProfileService, leaveRequestBase);

        // Give the menus to the login page
        LoginPage loginPage = new LoginPage(adminMenu, employeeMenu, repo);

        CheckPassword checkpassword = new CheckPassword();
        int counter = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password to login:  ");
        String input = scanner.nextLine();

        if (checkpassword.Check(input)) {
            loginPage.runLoginPage();
        } else {
            while (counter < 2) {
                System.out.printf("Incorrect password. Attempt Remaining: %d%n", 2 - counter);
                System.out.print("Try Again:  ");
                String retryInput = scanner.nextLine();
                if (checkpassword.Check(retryInput)) {
                    loginPage.runLoginPage();
                    break;
                }
                counter++;
            }
        }
    }
}
