package Abstract;

import Services.EmployeeService;
import java.util.Scanner;

public class HireFireBase {
    private EmployeeService empService;
    private Scanner scanner;

    public HireFireBase(EmployeeService empService) {
        this.empService = empService;
        this.scanner = new Scanner(System.in);
    }

    public void HireMenu() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Role (Admin/HR/Employee/Intern): ");
        String role = scanner.nextLine();

        System.out.print("Salary: ");
        int salary = Integer.parseInt(scanner.nextLine());

        System.out.print("Department: ");
        String dept = scanner.nextLine();

        System.out.print("Position: ");
        String pos = scanner.nextLine();

        empService.HireEmployee(role, name, salary, dept, pos);
    }

    public void FireMenu() {
        System.out.print("Enter Employee ID to fire: ");
        int id = Integer.parseInt(scanner.nextLine());
        empService.FireEmployee(id);
    }
}
