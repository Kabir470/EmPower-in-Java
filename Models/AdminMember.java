package Models;

import Abstract.EmployeeBase;
import Interfaces.IDocumentAdminAccess;
import Strategy.AdminSalaryStrategy;

public class AdminMember extends EmployeeBase implements IDocumentAdminAccess {
    public AdminMember(int id, String name, int salary, String department, String position) {
        super(id, name, salary, department, position);
        this.salaryStrategy = new AdminSalaryStrategy();
    }

    @Override
    public String GetRole() {
        return "Admin";
    }

    @Override
    public void CreateDocument() {
        System.out.println(getName() + " Admin created a document.");
    }

    @Override
    public void UpdateDocument() {
        System.out.println(getName() + " Admin updated a document.");
    }

    @Override
    public void DeleteDocument() {
        System.out.println(getName() + " Admin deleted a document.");
    }

    @Override
    public void ReadDocument() {
        System.out.println(getName() + " Admin read a document.");
    }
}
