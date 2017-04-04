package controller;

import model.validation.Notification;
import model.validation.ResultFetchException;
import service.onUser.AuthentificationService;
import service.onUser.UserService;
import view.AdministratorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hanna on 27.03.2017.
 */
public class AdministratorController {


    private final AdministratorFrame administratorFrame;
    private final AuthentificationService authenticationService;
    private UserService userService;



    public AdministratorController(AdministratorFrame administratorFrame, AuthentificationService authenticationService, UserService userService) {
        this.administratorFrame = administratorFrame;
        this.authenticationService = authenticationService;
        this.userService = userService;
        administratorFrame.addActionListenerToAddUserButton(new AddButtonActionListener());
        administratorFrame.addActionListenerToDeleteUserButton(new DeleteActionListener());
        administratorFrame.addActionListenerToViewAllUsersButton(new ViewAllActionListener());
        administratorFrame.addActionListenerToGenerateReportButton(new GenerateReportActionListener());
    }

    private class AddButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username= administratorFrame.getTextUsername();
            String password  = administratorFrame.getTextPass();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(administratorFrame.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                try {
                    if (registerNotification.getResult()) {
                        JOptionPane.showMessageDialog(administratorFrame.getContentPane(), "Registration successful!");
                    } else {

                        JOptionPane.showMessageDialog(administratorFrame.getContentPane(), "Registration not successful, please try again later.");
                    }
                } catch (ResultFetchException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

    private class DeleteActionListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            String username = administratorFrame.getTextUsername();
            if(!username.isEmpty()){
                userService.deleteByUsername(username);
                JOptionPane.showMessageDialog(administratorFrame.getContentPane(), "Successfully Deleted!");
            }else
                JOptionPane.showMessageDialog(administratorFrame.getContentPane(), "Add a Client Username!");

        }
    }

    private class ViewAllActionListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

              administratorFrame.setTextPane(userService.findAll().toString());

        }
    }

    private class GenerateReportActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String employeeUserName= administratorFrame.getTextUsername();
            if(!employeeUserName.isEmpty()){
                try {
                    administratorFrame.setTextPane(userService.generateReportOfEmployee(Long.parseLong(employeeUserName)).toString());
                }catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(administratorFrame.getContentPane(), "Give a valid number");
                }
            }

        }
    }



    public void run() {
        administratorFrame.setVisible(true);
    }

}
