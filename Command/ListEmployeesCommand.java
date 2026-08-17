package Command;

import Services.EmployeeService;

public class ListEmployeesCommand implements ICommand {
    private EmployeeService empService;

    public ListEmployeesCommand(EmployeeService empService) {
        this.empService = empService;
    }

    @Override
    public void Execute() {
        empService.ListAll();
    }
}
