package Models;

import Abstract.EmployeeBase;
import Interfaces.IDocumentEmployeeAccess;
import Interfaces.IEmployee;
import Strategy.InternSalaryStrategy;

public class InternEmployee extends EmployeeBase implements IEmployee, IDocumentEmployeeAccess {
    public InternEmployee(int id, String name, int salary, String department, String position) {
        super(id, name, salary, department, position);
        this.salaryStrategy = new InternSalaryStrategy();
    }

    @Override
    public String GetRole() {
        return "Intern";
    }

    @Override
    public void ReadDocument() {
        System.out.println(getName() + " Intern read a document.");
    }
}
