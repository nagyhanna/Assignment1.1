package controller;

import model.Account;
import model.Client;
import model.Report;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.builder.ReportBuilder;
import repository.EntityNotFoundException;
import service.onAccount.AccountService;
import service.onClient.ClientService;
import service.onUser.UserService;
import view.EmployeeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by hanna on 27.03.2017.
 */
public class EmployeeController {

    private final EmployeeFrame employeeFrame;
    private final ClientService clientService;
    private final AccountService accountService;
    private final UserService userService;
    private Long currentEmployeeLoggedId;


    public EmployeeController(EmployeeFrame employeeFrame, ClientService clientService, AccountService accountService,
                              UserService userService) {
        this.employeeFrame = employeeFrame;
        this.clientService = clientService;
        this.accountService = accountService;
        this.userService = userService;
        employeeFrame.addActionListenerToAddClientButton(new AddClientActionListener());
        employeeFrame.addActionListenerToAddAccountButton(new AddAccountActionListener());
        employeeFrame.addActionListenerToDeleteAccountButton(new DeleteAccountActionListener());
        employeeFrame.addActionListenerToUpdateClientButton(new UpdateClientActionListener());
        employeeFrame.addActionListenerToViewAccountsButton(new ViewAllAccountsActionListener());
        employeeFrame.addActionListenerToViewAllClientsButton(new ViewAllClientsActionListener());
        employeeFrame.addActionListenerToTransferMoneyButton(new TransferMoneyActionListener());
        employeeFrame.addActionListenerToUpdatAccounteButton(new UpdateAccountButton());
    }

    private class AddClientActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = employeeFrame.getTf_firstName();
            String lastName = employeeFrame.getTf_lastName();
            String cardIdentityNr = employeeFrame.getTf_identityCardNr();
            String personalCardNr = employeeFrame.getTf_personalCardNr();
            String address = employeeFrame.getTf_address();
            Client newClient = new ClientBuilder().setFirstName(firstName).setLastName(lastName).setIdentityCardNr(cardIdentityNr).setPersonalCardNr(personalCardNr).setAddress(address).build();
            if (clientService.save(newClient)) {
                sendReport(currentEmployeeLoggedId, "new Client was created");
                JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Client Saved");
            }


        }
    }

    private class AddAccountActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long client_id;
            String type = employeeFrame.getTf_accountType();
            Double balance;

            try {
                client_id = Long.parseLong(employeeFrame.getTf_clientId());
                balance = Double.parseDouble(employeeFrame.getTf_amountOfMoney());
                Account newAccount = new AccountBuilder().setClientId(client_id).setAmountOfMoney(balance).setType(type).setDateOfCreation(new Date()).build();
                if (accountService.save(newAccount)) {
                    sendReport(currentEmployeeLoggedId, "new Account was created to client_id"+ client_id);
                    JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Account Saved");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Give a valid number for client or amount");
            }
        }
    }

    private class DeleteAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Long accountId = Long.parseLong(employeeFrame.getTf_accountId());
                accountService.delete(accountId);
                sendReport(currentEmployeeLoggedId, "Account with id: " + accountId+" was deleted");
                JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Account Deleted");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Give a valid number");
            }
        }
    }

    private class UpdateClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            Long client_id = Long.parseLong(employeeFrame.getTf_clientId());
            String firstName = employeeFrame.getTf_firstName();
            String lastName = employeeFrame.getTf_lastName();
            String cardIdentityNr = employeeFrame.getTf_identityCardNr();
            String personalCardNr = employeeFrame.getTf_personalCardNr();
            String address = employeeFrame.getTf_address();
            Client newClient = new ClientBuilder().setId(client_id).setFirstName(firstName).setLastName(lastName).setIdentityCardNr(cardIdentityNr).setPersonalCardNr(personalCardNr).setAddress(address).build();
            if (clientService.update(newClient)) {
                sendReport(currentEmployeeLoggedId, "Client with id: "+client_id +"was updated");
                JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Client Updated");
            }}
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Give valid number to id");
            }
        }
    }

    private class ViewAllAccountsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String client_id = employeeFrame.getTf_clientId();
                try {

                    Long id = Long.parseLong(client_id);
                    employeeFrame.setTextPane(accountService.findAllAccountsOfAClient(id).toString());
                    sendReport(currentEmployeeLoggedId, "Viewed all accounts of client: "+client_id);


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Give a valid number format client id");
                    employeeFrame.setTextPane(accountService.findAll().toString());
                    sendReport(currentEmployeeLoggedId, "Viewed all accounts");

                } catch (EntityNotFoundException e1) {
                    JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "there is not such account ");
                }
            }
    }


    private class ViewAllClientsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            employeeFrame.setTextPane(clientService.findAll().toString());
            sendReport(currentEmployeeLoggedId, "Viewed all clients");
        }
    }

    private class TransferMoneyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                Long accountIdTo = Long.parseLong(employeeFrame.getTf_accountId());
                Long accountIdFrom = Long.parseLong(employeeFrame.getTf_accountId2());
                Double sumOfMoney = Double.parseDouble(employeeFrame.getTf_amountOfMoney());
                accountService.transferMoneyBetweenAccounts(accountIdFrom, accountIdTo, sumOfMoney);
                sendReport(currentEmployeeLoggedId, sumOfMoney + " was tranfered from "+ accountIdFrom +" to "
                + accountIdFrom);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Give a valid number format account " + "ids or amount of money");
            } catch (EntityNotFoundException e1) {
                JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "there is not such account ");
            }
        }
    }


    private class UpdateAccountButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Long account_id = Long.parseLong(employeeFrame.getTf_accountId());
                Long client_id = Long.parseLong(employeeFrame.getTf_clientId());
                Double sumOfMoney = Double.parseDouble(employeeFrame.getTf_amountOfMoney());
                String type = employeeFrame.getTf_accountType();
                Account account = new AccountBuilder().setId(account_id).setType(type).setAmountOfMoney(sumOfMoney).setClientId(client_id).setDateOfCreation(new Date()).build();

                if (accountService.update(account)) {
                    sendReport(currentEmployeeLoggedId, "Account with id:"+account_id+" was updated");
                    JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Account updated");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(employeeFrame.getContentPane(), "Give a valid number format account " + "ids or amount of money");
            }
        }
    }


    public void run(Long currentEmployeeLoggedId) {
        this.currentEmployeeLoggedId = currentEmployeeLoggedId;
        employeeFrame.setVisible(true);
    }

    private void sendReport(Long currentEmployeeLoggedId, String reportMessage){
        Report report = new ReportBuilder()
                .setUser_id(currentEmployeeLoggedId)
                .setReport(reportMessage)
                .setDate(new Date())
                .build();
        userService.addReportToUser(report);
    }
}


