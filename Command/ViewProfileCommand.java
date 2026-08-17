package Command;

import Services.EmployeeProfileServices;

public class ViewProfileCommand implements ICommand {
    private EmployeeProfileServices profileServices;
    private int empID;

    public ViewProfileCommand(EmployeeProfileServices profileServices, int empID) {
        this.profileServices = profileServices;
        this.empID = empID;
    }

    @Override
    public void Execute() {
        profileServices.ViewProfile(empID);
    }
}
