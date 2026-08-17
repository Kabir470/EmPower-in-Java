package Models;

import Abstract.EmployeeBase;
import Interfaces.IDocumentEmployeeAccess;
import Strategy.EmployeeSalaryStrategy;

public class Employee extends EmployeeBase implements IDocumentEmployeeAccess {
    public Employee(int id, String name, int salary, String department, String position) {
        super(id, name, salary, department, position);
        this.salaryStrategy = new EmployeeSalaryStrategy();
    }

    @Override
    public String GetRole() {
        return "Employee";
    }

    @Override
    public void ReadDocument() {
        System.out.println(getName() + " Employee read a document.");
    }
}
