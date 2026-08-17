package Facade;

import Abstract.CheckPassword;
import Abstract.LeaveRequestBase;
import Login.LoginPage;
import MenuUI.AdminMenuHandler;
import MenuUI.EmployeeMenuHandler;
import Observer.HRLeaveObserver;
import Repository.EmployeeRepository;
import Repository.LeaveRepository;
import Services.EmployeeProfileServices;
import Services.EmployeeService;
import Services.LeaveService;

import java.util.Scanner;

public class EmPowerFacade {
    private EmployeeRepository repo;
    private LeaveRepository leaveRepo;
    private EmployeeService empService;
    private EmployeeProfileServices empProfileService;
    private LeaveService leaveService;
    private LeaveRequestBase leaveRequestBase;
    private AdminMenuHandler adminMenu;
    private EmployeeMenuHandler employeeMenu;
    private LoginPage loginPage;
    private CheckPassword checkPassword;

    public EmPowerFacade() {
        // Initialize Singletons
        repo = EmployeeRepository.getInstance();
        leaveRepo = LeaveRepository.getInstance();

        // Register Observer
        leaveRepo.AddObserver(new HRLeaveObserver());

        // Wire Services
        empService = new EmployeeService(repo);
        empProfileService = new EmployeeProfileServices(repo);
        leaveService = new LeaveService(leaveRepo, repo);

        // Wire Handlers
        leaveRequestBase = new LeaveRequestBase(leaveService, leaveRepo);
        adminMenu = new AdminMenuHandler(empService, leaveRequestBase);
        employeeMenu = new EmployeeMenuHandler(empProfileService, leaveRequestBase);
        loginPage = new LoginPage(adminMenu, employeeMenu, repo);
        checkPassword = new CheckPassword();
    }

    public void StartSystem() {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;

        System.out.print("Enter password to login:  ");
        String input = scanner.nextLine();

        if (checkPassword.Check(input)) {
            loginPage.runLoginPage();
        } else {
            while (counter < 2) {
                System.out.printf("Incorrect password. Attempt Remaining: %d%n", 2 - counter);
                System.out.print("Try Again:  ");
                String retryInput = scanner.nextLine();
                if (checkPassword.Check(retryInput)) {
                    loginPage.runLoginPage();
                    break;
                }
                counter++;
            }
        }
    }
}
