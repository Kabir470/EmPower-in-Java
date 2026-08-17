package Strategy;

public class InternSalaryStrategy implements ISalaryStrategy {
    @Override
    public void CalculateSalary(String name, int salary) {
        System.out.println(" " + name + " salary: " + salary + " (Interns receive a fixed stipend)");
    }
}
