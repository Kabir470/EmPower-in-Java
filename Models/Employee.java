package Models;

import Abstract.EmployeeBase;
import Interfaces.IDocumentEmployeeAccess;

public class Employee extends EmployeeBase implements IDocumentEmployeeAccess {
    public Employee(int id, String name, int salary, String department, String position) {
        super(id, name, salary, department, position);
    }

    @Override
    public void CalculateSalary(int employeeID) {
        System.out.println(" " + getName() + " salary: " + getSalary());
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
