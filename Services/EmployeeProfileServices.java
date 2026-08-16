package Services;

import Abstract.EmployeeBase;
import Repository.EmployeeRepository;
import java.util.Scanner;

public class EmployeeProfileServices {
    private EmployeeRepository repo;
    private Scanner scanner;

    public EmployeeProfileServices(EmployeeRepository repo) {
        this.repo = repo;
        this.scanner = new Scanner(System.in);
    }

    public void ViewProfile(int eID) {
        EmployeeBase emp = repo.GetByID(eID);
        if (emp == null) {
            System.out.println(" Employee not found!...");
            scanner.nextLine();
            return;
        }
        emp.PrintDetails();
        scanner.nextLine();
    }
}
