package service.onAccount;

import database.DBConnectionFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMYSQLImpl;

import java.sql.Connection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hanna on 28.03.2017.
 */
public class AccountServiceImplTest {

    private static AccountRepository accountRepository;
    private static AccountService accountService;

    @BeforeClass
    public static void setupClass() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
        accountRepository = new AccountRepositoryMYSQLImpl(connection);
        accountService = new AccountServiceImpl(accountRepository);

    }

    @Test
    public void findAll() throws Exception {

        accountService.removeAll();
        accountService.save(new AccountBuilder().setClientId(1L).setType("Saving")
                .setAmountOfMoney(23.00).setDateOfCreation(new Date()).build());
        assertEquals(accountService.findAll().size(),1);

    }

    @Test
    public void findById() throws Exception {

        //accountService.removeAll();
        //accountService.save(new AccountBuilder().setClientId(1L).setType("Saving").setAmountOfMoney(23.00).setDateOfCreation(new Date()).build());
        assertNotNull(accountService.findById(15L));
    }

    @Test
    public void findAllAccountsOfAClient() throws Exception {

        assertNotNull(accountService.findAllAccountsOfAClient(1L));
    }

    @Test
    public void save() throws Exception {
        accountService.removeAll();

        assertTrue(accountService.save(new AccountBuilder().setClientId(1L).setType("Saving")
                .setAmountOfMoney(23.00).setDateOfCreation(new Date()).build()));

    }

    @Test
    public void delete() throws Exception {

        assertTrue(accountService.delete(8L));


    }

    @Test
    public void update() throws Exception {

        accountService.removeAll();
        Account account = new AccountBuilder().setClientId(1L).setType("Saving").setAmountOfMoney(23.00).setDateOfCreation(new Date()).build();
        accountService.save(account);
        account.setAmountOfMoney(99999.9);
        assertTrue(accountService.update(account));

    }

    @Test
    public void removeAll() throws Exception {
        accountService.removeAll();
        assertEquals(accountService.findAll().size(),0);

    }

    @Test
    public void withdrawMoney() throws Exception {
        assertTrue( accountService.withdrawMoney(15L, 20.0));

    }

    @Test
    public void addMoney() throws Exception {
        assertTrue( accountService.addMoney(15L, 120.0));

    }


    @Test
    public void transferMoneyBetweenAccounts() throws Exception {
        assertTrue( accountService.transferMoneyBetweenAccounts(15L, 16L, 90.0));

    }
}