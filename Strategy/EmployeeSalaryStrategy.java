package Strategy;

public class EmployeeSalaryStrategy implements ISalaryStrategy {
    @Override
    public void CalculateSalary(String name, int salary) {
        System.out.println(" " + name + " salary: " + salary);
    }
}
