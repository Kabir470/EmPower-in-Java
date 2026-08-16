package Models;

import Abstract.EmployeeBase;
import Interfaces.IDocumentEmployeeAccess;
import Interfaces.IEmployee;

public class InternEmployee extends EmployeeBase implements IEmployee, IDocumentEmployeeAccess {
    public InternEmployee(int id, String name, int salary, String department, String position) {
        super(id, name, salary, department, position);
    }

    @Override
    public String GetRole() {
        return "Intern";
    }

    @Override
    public void CalculateSalary(int internID) {
        System.out.println(" " + getName() + " salary: " + getSalary() + " (Interns receive a fixed stipend)");
    }

    @Override
    public void ReadDocument() {
        System.out.println(getName() + " Intern read a document.");
    }
}
