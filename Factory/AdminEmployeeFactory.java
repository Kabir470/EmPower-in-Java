package Factory;

import Abstract.EmployeeBase;
import Models.AdminMember;

public class AdminEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeBase CreateEmployee(int id, String name, int salary, String dept, String pos) {
        return new AdminMember(id, name, salary, dept, pos);
    }
}
