package Models;

import Abstract.EmployeeBase;
import Interfaces.IDocumentAdminAccess;
import Strategy.HrSalaryStrategy;

public class HrMember extends EmployeeBase implements IDocumentAdminAccess {
    public HrMember(int id, String name, int salary, String department, String position) {
        super(id, name, salary, department, position);
        this.salaryStrategy = new HrSalaryStrategy();
    }

    @Override
    public String GetRole() {
        return "HR";
    }

    @Override
    public void CreateDocument() {
        System.out.println(getName() + " HR created a document.");
    }

    @Override
    public void UpdateDocument() {
        System.out.println(getName() + " HR updated a document.");
    }

    @Override
    public void DeleteDocument() {
        System.out.println(getName() + " HR deleted a document.");
    }

    @Override
    public void ReadDocument() {
        System.out.println(getName() + " HR read a document.");
    }
}
