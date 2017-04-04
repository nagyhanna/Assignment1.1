package repository.account;

import model.Account;
import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public interface AccountRepository {


    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    List<Account> findAllAccountsOfAClient(Long client_id) throws EntityNotFoundException;

    boolean save(Account account);

    boolean delete(Long id);

    boolean update(Account account);

    void removeAll();

    void removeAllAccountsOfAClient(Long client_id) throws EntityNotFoundException;


}
