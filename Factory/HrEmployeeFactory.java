package Factory;

import Abstract.EmployeeBase;
import Models.HrMember;

public class HrEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeBase CreateEmployee(int id, String name, int salary, String dept, String pos) {
        return new HrMember(id, name, salary, dept, pos);
    }
}
