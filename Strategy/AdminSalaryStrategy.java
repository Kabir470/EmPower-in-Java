package Strategy;

public class AdminSalaryStrategy implements ISalaryStrategy {
    @Override
    public void CalculateSalary(String name, int salary) {
        System.out.println(" " + name + " salary: " + salary + " + Admin allowance: " + (salary / 5));
    }
}
