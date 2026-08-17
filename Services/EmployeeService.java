package Services;

import Abstract.EmployeeBase;
import Factory.EmployeeFactory;
import Repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    private EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public EmployeeService() {
        this.repo = EmployeeRepository.getInstance();
    }

    public void HireEmployee(String role, String name, int salary, String dept, String pos) {
        int id = repo.GenerateID();
        EmployeeBase emp = EmployeeFactory.CreateEmployee(role, id, name, salary, dept, pos);

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
