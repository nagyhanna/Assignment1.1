package controller;

import model.User;
import model.validation.Notification;
import service.onUser.AuthentificationService;
import service.onUser.UserService;
import view.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static database.Constants.Roles.EMPLOYEE;

/**
 * Created by hanna on 27.03.2017.
 */
public class LoginController {

    private final LoginFrame loginFrame;
    private final AdministratorController administratorController;
    private final EmployeeController employeeController;
    private final UserService userService;
    private final AuthentificationService authenticationService;

    public LoginController(LoginFrame loginFrame, AdministratorController administratorController,
                           EmployeeController employeeController, UserService userService, AuthentificationService authenticationService) {
        this.loginFrame = loginFrame;
        this.administratorController = administratorController;
        this.employeeController = employeeController;
        this.userService = userService;
        this.authenticationService = authenticationService;
        loginFrame.setLoginButtonListener(new LoginButtonListener());

    }

    private class LoginButtonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginFrame.getUsername();
            String password = loginFrame.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);
            if (loginNotification.hasErrors()) {

                JOptionPane.showMessageDialog(loginFrame.getContentPane(), loginNotification.getFormattedErrors());

            } else {
                JOptionPane.showMessageDialog(loginFrame.getContentPane(), "Login successful!");
                Long userId= userService.findByUserName(username).getId();


                if(authenticationService.getRolesOfUser(userId).get(0).getRole().equals(EMPLOYEE)) {
                    employeeController.run(userId);
                    loginFrame.setVisible(false);
                }else {
                    administratorController.run();
                    loginFrame.setVisible(false);
                }
            }

            }

    }


}
