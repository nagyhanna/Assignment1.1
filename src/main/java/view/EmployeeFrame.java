package view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by hanna on 27.03.2017.
 */
public class EmployeeFrame extends JFrame {

    private JScrollPane scrollPane;
    private JTextPane textPane;

    private JTextField tf_firstName;
    private JTextField tf_lastName;
    private JTextField tf_clientId;
    private JTextField tf_accountId;
    private JTextField tf_accountId2;
    private JTextField tf_identityCardNr;
    private JTextField tf_personalCardNr;
    private JTextField tf_accountType;
    private JTextField tf_address;
    private JTextField tf_amountOfMoney;

    private JButton addClientButton;
    private JButton transferMoneyButton;
    private JButton viewAllClientsButton;
    private JButton updateClientButton;

    private JButton addAccountButton;
    private JButton deleteAccountButton;
    private JButton updateAccountButton;
    private JButton viewAccountsButton;


    public EmployeeFrame() {
        super("Employee Frame ");

        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(null);

        tf_firstName = new JTextField("First Name");
        tf_lastName = new JTextField("Last Name");
        tf_clientId= new JTextField("Client Id");
        tf_accountId= new JTextField("Account Id From");
        tf_accountId2= new JTextField("Account Id To");
        tf_identityCardNr= new JTextField("Identity Card Nr");
        tf_personalCardNr= new JTextField("Personal Card Nr");
        tf_accountType= new JTextField("Account Type");
        tf_address= new JTextField("Address");
        tf_amountOfMoney= new JTextField("Amount Of money");


        addClientButton = new JButton("Add Client");
        transferMoneyButton = new JButton("Transfer Money");
        viewAllClientsButton = new JButton("All Clients");
        updateClientButton = new JButton("Update Client");
        addAccountButton=new JButton("Add Account");
        deleteAccountButton =new JButton("Delete Account");
        updateAccountButton =new JButton("Update Account");
        viewAccountsButton=new JButton("View Accounts");


        tf_firstName.setBounds(0, 0, 120, 20);
        tf_lastName.setBounds(0, 20, 120, 20);
        tf_clientId.setBounds(0, 40, 120, 20);
        tf_accountId.setBounds(0, 60, 120, 20);
        tf_accountId2.setBounds(0, 80, 120, 20);
        tf_identityCardNr.setBounds(0, 180, 120, 20);
        tf_personalCardNr.setBounds(0, 100, 120, 20);
        tf_accountType.setBounds(0, 120, 120, 20);
        tf_address.setBounds(0, 140, 120, 20);
        tf_amountOfMoney.setBounds(0, 160, 120, 20);

        addClientButton.setBounds(130, 40 , 120, 20);
        transferMoneyButton.setBounds(130, 0, 120, 20);
        viewAllClientsButton.setBounds(130, 20, 120, 20);
        updateClientButton.setBounds(130, 60 , 160, 20);
        //updateClientButton.setBounds(130, 80 , 160, 20);
        addAccountButton.setBounds(130, 100 , 160, 20);
        deleteAccountButton.setBounds(130, 120 , 160, 20);
        updateAccountButton.setBounds(130, 140 , 160, 20);
        viewAccountsButton.setBounds(130, 160 , 160, 20);

        scrollPane = new JScrollPane();
        textPane = new JTextPane();
        textPane.setEnabled(false);
        scrollPane.setViewportView(textPane);
        scrollPane.setBounds(10, 200, 380, 100);

        add(tf_firstName);
        add(tf_lastName);
        add(tf_clientId);
        add(tf_accountId);
        add(tf_accountId2);
        add(tf_identityCardNr);
        add(tf_personalCardNr);
        add(tf_accountType);
        add(tf_address);
        add(tf_amountOfMoney);

        add(addClientButton);
        add(scrollPane);
        add(transferMoneyButton);
        add(viewAllClientsButton);
        add(updateClientButton);
        add(addAccountButton);
        add(deleteAccountButton);
        add(updateAccountButton);
        add(viewAccountsButton);

        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public void addActionListenerToAddClientButton(ActionListener actionListener){
        addClientButton.addActionListener(actionListener);
    }
    public void addActionListenerToTransferMoneyButton(ActionListener actionListener){
        transferMoneyButton.addActionListener(actionListener);
    }
    public void addActionListenerToViewAllClientsButton(ActionListener actionListener){
        viewAllClientsButton.addActionListener(actionListener);
    }
    public void addActionListenerToUpdateClientButton(ActionListener actionListener){
        updateClientButton.addActionListener(actionListener);
    }
    public void addActionListenerToAddAccountButton(ActionListener actionListener){
        addAccountButton.addActionListener(actionListener);
    }
    public void addActionListenerToDeleteAccountButton(ActionListener actionListener){
        deleteAccountButton.addActionListener(actionListener);

    }
    public void addActionListenerToUpdatAccounteButton(ActionListener actionListener){
        updateAccountButton.addActionListener(actionListener);
    }
    public void addActionListenerToViewAccountsButton(ActionListener actionListener){
        viewAccountsButton.addActionListener(actionListener);
    }

   public void setTextPane(String string){
        textPane.setEnabled(true);
        textPane.setText(string);
   }

    public String getTf_clientId() {
        return tf_clientId.getText();
    }

    public String getTf_accountId() {
        return tf_accountId.getText();
    }
    public String getTf_accountId2() {
        return tf_accountId2.getText();
    }

    public String getTf_identityCardNr() {
        return tf_identityCardNr.getText();
    }

    public String getTf_personalCardNr() {
        return tf_personalCardNr.getText();
    }

    public String getTf_firstName() {
        return tf_firstName.getText();
    }

    public String getTf_lastName() {
        return tf_lastName.getText();
    }

    public String getTf_accountType() {
        return tf_accountType.getText();
    }

    public String getTf_address() {
        return tf_address.getText();
    }

    public String getTf_amountOfMoney() {

       return tf_amountOfMoney.getText();
    }
}
