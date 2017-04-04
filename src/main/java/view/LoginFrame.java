package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by hanna on 27.03.2017.
 */
public class LoginFrame extends JFrame {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;


    public LoginFrame() {
        super("Login Frame");
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        getContentPane().setBackground(new Color(255, 218, 252));
        //  setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLayout(null);
        add(tfUsername);
        add(pfPassword);
        add(btnLogin);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        tfUsername = new JTextField("username");
        pfPassword = new JPasswordField();
        btnLogin = new JButton("Login");


        tfUsername.setBounds(60, 50, 160, 40);
        pfPassword.setBounds(60, 90, 160, 40);
        btnLogin.setBounds(60, 130, 160, 40);

    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return pfPassword.getText();
    }


    public void setLoginButtonListener(ActionListener loginButtonListener) {
        btnLogin.addActionListener(loginButtonListener);
    }


}
