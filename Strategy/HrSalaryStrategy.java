package Strategy;

public class HrSalaryStrategy implements ISalaryStrategy {
    @Override
    public void CalculateSalary(String name, int salary) {
        System.out.println(" " + name + " salary: " + salary + " + HR allowance: " + (salary / 10));
    }
}
