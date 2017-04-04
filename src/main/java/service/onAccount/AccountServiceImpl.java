package service.onAccount;

import model.Account;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public List <Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return accountRepository.findById(id);
    }

    @Override
    public List <Account> findAllAccountsOfAClient(Long client_id) throws EntityNotFoundException {
        return accountRepository.findAllAccountsOfAClient(client_id);
    }

    @Override
    public boolean save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public boolean delete(Long id) {
        return accountRepository.delete(id);
    }

    @Override
    public boolean update(Account account) {
        return accountRepository.update(account);
    }

    @Override
    public void removeAll() {
        accountRepository.removeAll();

    }

    @Override
    public void removeAllAccountsOfAClient(Long client_id) throws EntityNotFoundException {
        accountRepository.removeAllAccountsOfAClient(client_id);
    }

    @Override
    public boolean withdrawMoney(Long id, Double amountOfMoney) throws EntityNotFoundException {

        Account account = accountRepository.findById(id);
        Double currentBalance = account.getAmountOfMoney();
        if (currentBalance >= amountOfMoney) {

            account.setAmountOfMoney(currentBalance - amountOfMoney);
            accountRepository.update(account);
        } else return false;
        return true;
    }


    @Override
    public boolean addMoney(Long id, Double amountOfMoney) throws EntityNotFoundException {

        Account account = accountRepository.findById(id);
        Double currentBalance = account.getAmountOfMoney();
        account.setAmountOfMoney(currentBalance + amountOfMoney);
        if (accountRepository.update(account)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean transferMoneyBetweenAccounts(Long fromAccount_id, Long toAccount_id, Double amountOfMoney) throws EntityNotFoundException {

        if (withdrawMoney(fromAccount_id, amountOfMoney)) {
            addMoney(toAccount_id, amountOfMoney);
        } else return false;

        return true;

    }
}
