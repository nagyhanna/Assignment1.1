import controller.AdministratorController;
import controller.EmployeeController;
import controller.LoginController;
import view.AdministratorFrame;
import view.EmployeeFrame;
import view.LoginFrame;


/**
 * Created by hanna on 26.03.2017.
 */
public class Launcher {


    public static void main(String[] args) {

        ComponentFactory componentFactory = ComponentFactory.instance();
        AdministratorController administratorController = new AdministratorController(new AdministratorFrame(),
                componentFactory.getAuthenticationService(),
                componentFactory.getUserService());
        EmployeeController employeeController = new EmployeeController(new EmployeeFrame(),
                componentFactory.getClientService(), componentFactory.getAccountService(),
                componentFactory.getUserService());
        new LoginController(new LoginFrame(), administratorController, employeeController,
                componentFactory.getUserService(), componentFactory.getAuthenticationService());

    }
}
