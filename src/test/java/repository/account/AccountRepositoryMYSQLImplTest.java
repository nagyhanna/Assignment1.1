package repository.account;

import database.DBConnectionFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by hanna on 28.03.2017.
 */
public class AccountRepositoryMYSQLImplTest {

    private static AccountRepository accountRepository;

    @BeforeClass
    public static void setupClass() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
        accountRepository = new AccountRepositoryMYSQLImpl(connection);

    }

    @Test
    public void findAll() throws Exception {

       // accountRepository.removeAll();
        accountRepository.save(new AccountBuilder().setClientId(1L).setType("Saving").setAmountOfMoney(23.00).setDateOfCreation(new Date()).build());
        assertNotNull(accountRepository.findAll());

    }

    @Test
    public void findById() throws Exception {

        //accountRepository.removeAll();
        //accountRepository.save(new AccountBuilder().setClientId(1L).setType("Saving").setAmountOfMoney(23.00).setDateOfCreation(new Date()).build());
        assertNotNull(accountRepository.findById(5L));
    }

    @Test
    public void findAllAccountsOfAClient() throws Exception {

        assertNotNull(accountRepository.findAllAccountsOfAClient(1L));
    }

    @Test
    public void save() throws Exception {
        //accountRepository.removeAll();

        assertTrue(accountRepository.save(new AccountBuilder().setClientId(1L).setType("Saving")
                .setAmountOfMoney(23.00).setDateOfCreation(new Date()).build()));

    }

    @Test
    public void delete() throws Exception {

       assertTrue(accountRepository.delete(6L));


    }

    @Test
    public void update() throws Exception {

        accountRepository.removeAll();
        Account account = new AccountBuilder().setClientId(1L).setType("Saving").setAmountOfMoney(23.00).setDateOfCreation(new Date()).build();
        accountRepository.save(account);
        account.setAmountOfMoney(99999.9);
        assertTrue(accountRepository.update(account));

    }

    @Test
    public void removeAll() throws Exception {
        accountRepository.removeAll();
        assertEquals(accountRepository.findAll().size(),0);

    }


}