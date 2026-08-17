package Factory;

import Abstract.EmployeeBase;
import Models.InternEmployee;

public class InternEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeBase CreateEmployee(int id, String name, int salary, String dept, String pos) {
        return new InternEmployee(id, name, salary, dept, pos);
    }
}
