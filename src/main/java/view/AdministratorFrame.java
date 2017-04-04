package view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by hanna on 27.03.2017.
 */
public class AdministratorFrame extends JFrame {

    private JScrollPane scrollPane;
    private JTextPane textPane;

    private JTextField textUsername;
    private JPasswordField textPass;
    
    private JButton addUserButton;
    private JButton deleteUserButton;
    private JButton viewAllUserButton;
    private JButton generateReportOfEmployeeButton;
    
    private JRadioButton employeeRole;

    public AdministratorFrame() {
        super("Administrator Frame ");

        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(null);

        textUsername = new JTextField("username");
        textPass = new JPasswordField();
        addUserButton = new JButton("Add User");
        deleteUserButton = new JButton("Delete User");
        viewAllUserButton = new JButton("All Users");
        generateReportOfEmployeeButton= new JButton("Generate Report");
        employeeRole = new JRadioButton("Role: Employee ", true);
        textPane = new JTextPane();
        textPane.setEnabled(false);

        textUsername.setBounds(0, 0, 120, 20);
        textPass.setBounds(0, 20, 120, 20);
        employeeRole.setBounds(0, 40, 120, 20);
        addUserButton.setBounds(130, 40 , 120, 20);
        deleteUserButton.setBounds(130, 0, 120, 20);
        viewAllUserButton.setBounds(130, 20, 120, 20);
        generateReportOfEmployeeButton.setBounds(130, 60 , 160, 20);


        scrollPane = new JScrollPane();
        scrollPane.setViewportView(textPane);
        scrollPane.setBounds(10, 200, 380, 100);

        add(textUsername);
        add(textPass);
        add(employeeRole);
        add(addUserButton);
        add(scrollPane);
        add(deleteUserButton);
        add(viewAllUserButton);
        add(generateReportOfEmployeeButton);

        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public void addActionListenerToAddUserButton(ActionListener actionListener){
        addUserButton.addActionListener(actionListener);
    }
    public void addActionListenerToDeleteUserButton(ActionListener actionListener){
        deleteUserButton.addActionListener(actionListener);
    }
    public void addActionListenerToViewAllUsersButton(ActionListener actionListener){
        viewAllUserButton.addActionListener(actionListener);
    }
    public void addActionListenerToGenerateReportButton(ActionListener actionListener){
        generateReportOfEmployeeButton.addActionListener(actionListener);
    }


    public String getTextUsername() {
        return textUsername.getText();
    }

    public String getTextPass() {
        return textPass.getText();
    }

    public JButton getAddUserButton() {
        return addUserButton;
    }

    public JButton getDeleteUserButton() {
        return deleteUserButton;
    }

    public JButton getViewAllUserButton() {
        return viewAllUserButton;
    }

    public JButton getGenerateReportOfEmployeeButton() {
        return generateReportOfEmployeeButton;
    }

    public JRadioButton getEmployeeRole() {
        return employeeRole;
    }

    public void setTextPane(String users) {
        textPane.setEnabled(true);
        textPane.setText(users);
    }
}
