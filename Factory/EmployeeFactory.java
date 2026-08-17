package Factory;

import Abstract.EmployeeBase;

public abstract class EmployeeFactory {
    public abstract EmployeeBase CreateEmployee(int id, String name, int salary, String dept, String pos);

    public static EmployeeBase Create(String role, int id, String name, int salary, String dept, String pos) {
        if (role == null) return null;
        EmployeeFactory factory = switch (role) {
            case "Admin", "AdminMember" -> new AdminEmployeeFactory();
            case "HR", "HrMember" -> new HrEmployeeFactory();
            case "Employee" -> new StandardEmployeeFactory();
            case "Intern", "InternEmployee" -> new InternEmployeeFactory();
            default -> null;
        };
        return (factory != null) ? factory.CreateEmployee(id, name, salary, dept, pos) : null;
    }
}
