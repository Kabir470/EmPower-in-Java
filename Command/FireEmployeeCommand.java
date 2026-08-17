package Command;

import Abstract.HireFireBase;

public class FireEmployeeCommand implements ICommand {
    private HireFireBase hirefire;

    public FireEmployeeCommand(HireFireBase hirefire) {
        this.hirefire = hirefire;
    }

    @Override
    public void Execute() {
        hirefire.FireMenu();
    }
}
