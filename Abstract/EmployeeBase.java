package Abstract;

import Interfaces.IEmployee;
import Interfaces.ISalaryCalculation;
import Strategy.ISalaryStrategy;

public abstract class EmployeeBase implements IEmployee, ISalaryCalculation {
    private int employeeID;
    private String name;
    private int salary;
    private String department;
    private String position;
    protected ISalaryStrategy salaryStrategy;

    @Override
    public int getEmployeeID() {
        return employeeID;
    }

    @Override
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Name cannot be null or empty.");
            return;
        }
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary < 0) {
            System.out.println("Salary cannot be negative.");
            return;
        }
        this.salary = salary;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setPosition(String position) {
        this.position = position;
    }

    public ISalaryStrategy getSalaryStrategy() {
        return salaryStrategy;
    }

    public void setSalaryStrategy(ISalaryStrategy salaryStrategy) {
        this.salaryStrategy = salaryStrategy;
    }

    public EmployeeBase(int id, String name, int salary, String department, String position) {
        setEmployeeID(id);
        setName(name);
        setSalary(salary);
        setDepartment(department);
        setPosition(position);
    }

    @Override
    public int CalculateBonus(int employeeID) {
        return getSalary() / 10;
    }

    public void ClockIn() {
        System.out.println(getName() + " clocked in.");
    }

    public void ClockOut() {
        System.out.println(getName() + " clocked out.");
    }

    public void PrintDetails() {
        System.out.println("------------------------------");
        System.out.println("Employee ID: " + getEmployeeID());
        System.out.println("Name: " + getName());
        System.out.println("Role: " + GetRole());
        System.out.println("Department: " + getDepartment());
        System.out.println("Position: " + getPosition());
        System.out.println("Bonus: " + CalculateBonus(getEmployeeID()));
        System.out.println("Salary: " + getSalary());
        System.out.println("------------------------------");
    }

    @Override
    public void CalculateSalary(int employeeID) {
        if (salaryStrategy != null) {
            salaryStrategy.CalculateSalary(getName(), getSalary());
        }
    }

    @Override
    public abstract String GetRole();
}
