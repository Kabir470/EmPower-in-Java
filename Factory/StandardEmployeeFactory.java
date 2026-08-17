package Factory;

import Abstract.EmployeeBase;
import Models.Employee;

public class StandardEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeBase CreateEmployee(int id, String name, int salary, String dept, String pos) {
        return new Employee(id, name, salary, dept, pos);
    }
}
