package Services;

import Abstract.EmployeeBase;
import Models.AdminMember;
import Models.Employee;
import Models.HrMember;
import Models.InternEmployee;
import Repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    private EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public EmployeeService() {
    }

    public void HireEmployee(String role, String name, int salary, String dept, String pos) {
        int id = repo.GenerateID();

        EmployeeBase emp = switch (role != null ? role : "") {
            case "Admin" -> new AdminMember(id, name, salary, dept, pos);
            case "HR" -> new HrMember(id, name, salary, dept, pos);
            case "Employee" -> new Employee(id, name, salary, dept, pos);
            case "Intern" -> new InternEmployee(id, name, salary, dept, pos);
            default -> null;
        };

        if (emp == null) {
            System.out.println(" Invalid role!");
            return;
        }
        repo.AddEmployee(emp);
    }

    public void FireEmployee(int id) {
        repo.RemoveEmployee(id);
    }

    public void ListAll() {
        List<EmployeeBase> all = repo.GetAllEmployees();
        if (all.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (EmployeeBase emp : all) {
            emp.PrintDetails();
        }
    }

    public void ViewProfile(int inputid) {
        EmployeeBase emp = repo.GetByID(inputid);

        if (emp == null) {
            System.out.println(" Employee not found!");
            return;
        }

        System.out.println("\n--- Employee Profile ---");
        emp.PrintDetails();
        System.out.println("------------------------\n");
    }
}
