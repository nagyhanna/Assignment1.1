package repository.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public class AccountRepositoryMYSQLImpl implements AccountRepository{

    private final Connection connection;

    public AccountRepositoryMYSQLImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }
    }

    @Override
    public List <Account> findAllAccountsOfAClient(Long client_id) throws EntityNotFoundException {
        List<Account> accounts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where client_id="+client_id;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?)");
            insertStatement.setLong(1, account.getClient_id());
            insertStatement.setString(2, account.getType());
            insertStatement.setDouble(3, account.getAmountOfMoney());
            insertStatement.setDate(4, new java.sql.Date(account.getDateOfCreation().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id =" + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Account account) {
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE account SET client_id = '" + account.getClient_id() +
                    "', type= '"+ account.getType()+"', amountOfMoney= "+ account.getAmountOfMoney()+" where id =" + account.getId();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAllAccountsOfAClient(Long client_id) throws EntityNotFoundException {

    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setClientId(rs.getLong("client_id"))
                .setType(rs.getString("type"))
                .setAmountOfMoney(rs.getDouble("amountOfMoney"))
                .setDateOfCreation(new Date(rs.getDate("dateOfCreation").getTime()))
                .build();
    }
}
