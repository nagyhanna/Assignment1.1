package service.onAccount;

import repository.EntityNotFoundException;
import repository.account.AccountRepository;

/**
 * Created by hanna on 26.03.2017.
 */
public interface AccountService extends AccountRepository {


    boolean withdrawMoney(Long id, Double amountOfMoney) throws EntityNotFoundException;
    boolean addMoney(Long id, Double amountOfMoney) throws EntityNotFoundException;
    boolean transferMoneyBetweenAccounts(Long fromAccount_id, Long toAccount_id, Double amountOfMoney) throws EntityNotFoundException;


}
