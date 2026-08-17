package Factory;

import Abstract.EmployeeBase;
import Models.AdminMember;
import Models.Employee;
import Models.HrMember;
import Models.InternEmployee;

public class EmployeeFactory {
    public static EmployeeBase CreateEmployee(String role, int id, String name, int salary, String dept, String pos) {
        if (role == null) return null;
        return switch (role) {
            case "Admin", "AdminMember" -> new AdminMember(id, name, salary, dept, pos);
            case "HR", "HrMember" -> new HrMember(id, name, salary, dept, pos);
            case "Employee" -> new Employee(id, name, salary, dept, pos);
            case "Intern", "InternEmployee" -> new InternEmployee(id, name, salary, dept, pos);
            default -> null;
        };
    }
}
