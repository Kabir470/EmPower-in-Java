package Repository;

import Abstract.EmployeeBase;
import Factory.EmployeeFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private static EmployeeRepository instance;
    private List<EmployeeBase> employees = new ArrayList<>();
    private int nextId = 1;
    private final String filePath = "users.txt";

    private EmployeeRepository() {
        LoadData();
    }

    public static synchronized EmployeeRepository getInstance() {
        if (instance == null) {
            instance = new EmployeeRepository();
        }
        return instance;
    }

    public void AddEmployee(EmployeeBase emp) {
        employees.add(emp);
        SaveData();
        System.out.println("Employee added: " + emp.getName() + " ");
    }

    public void RemoveEmployee(int employeeID) {
        EmployeeBase emp = GetByID(employeeID);
        if (emp == null) {
            System.out.println(" Employee not found!");
            return;
        }

        employees.remove(emp);
        SaveData();
        System.out.println(" " + emp.getName() + " removed.");
    }

    public EmployeeBase GetByID(int id) {
        return employees.stream()
                .filter(e -> e.getEmployeeID() == id)
                .findFirst()
                .orElse(null);
    }

    public List<EmployeeBase> GetAllEmployees() {
        return employees;
    }

    public int GenerateID() {
        return nextId++;
    }

    private void SaveData() {
        List<String> lines = new ArrayList<>();
        for (EmployeeBase emp : employees) {
            lines.add(emp.getClass().getSimpleName() + "|" + emp.getEmployeeID() + "|" + emp.getName() + "|" + emp.getSalary() + "|" + emp.getDepartment() + "|" + emp.getPosition());
        }

        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            System.err.println("Error saving employee data: " + e.getMessage());
        }
    }

    private void LoadData() {
        if (!Files.exists(Paths.get(filePath))) return;

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split("\\|");

                if (parts.length == 6) {
                    String roleType = parts[0];
                    int id = Integer.parseInt(parts[1]);
                    String name = parts[2];
                    int salary = Integer.parseInt(parts[3]);
                    String dept = parts[4];
                    String pos = parts[5];

                    EmployeeBase emp = EmployeeFactory.CreateEmployee(roleType, id, name, salary, dept, pos);

                    if (emp != null) {
                        employees.add(emp);

                        if (id >= nextId) {
                            nextId = id + 1;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading employee data: " + e.getMessage());
        }
    }
}
