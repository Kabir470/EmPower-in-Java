package Command;

import Abstract.HireFireBase;

public class HireEmployeeCommand implements ICommand {
    private HireFireBase hirefire;

    public HireEmployeeCommand(HireFireBase hirefire) {
        this.hirefire = hirefire;
    }

    @Override
    public void Execute() {
        hirefire.HireMenu();
    }
}
