package model.builder;

import model.Account;

import java.util.Date;

/**
 * Created by hanna on 26.03.2017.
 */
public class AccountBuilder {

    private Account account;

    public AccountBuilder(){
        account = new Account();
    }

    public AccountBuilder setId(Long id){
        account.setId(id);
        return this;
    }
    public AccountBuilder setClientId(Long client_id){
        account.setClient_id(client_id);
        return this;
    }

    public AccountBuilder setType(String accountType){

        account.setType(accountType);
        return this;

    }
    public AccountBuilder setAmountOfMoney(Double amountOfMoney){

        account.setAmountOfMoney(amountOfMoney);
        return this;

    }
    public AccountBuilder setDateOfCreation(Date date){

        account.setDateOfCreation(date);
        return this;

    }
    public Account build(){
        return account;
    }

}
