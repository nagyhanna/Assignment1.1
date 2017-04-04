package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public class ClientRespositoryMYSQLImpl implements ClientRepository {

    private final Connection connection;

    public ClientRespositoryMYSQLImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }


    }

    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, client.getFirstName());
            insertStatement.setString(2, client.getLastName());
            insertStatement.setString(3, client.getIdentityCardNr());
            insertStatement.setString(4, client.getPersonalCardNr());
            insertStatement.setString(5, client.getAddress());
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
            String sql = "DELETE from client where id =" + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Client client) {
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE client SET firstName = '" + client.getFirstName() +
                    "', lastName= '"+ client.getLastName()+"', identityCardNumber= '"+ client.getIdentityCardNr()+"', personalCardNumber= '"
                    +client.getPersonalCardNr()+"', address='"+client.getAddress()+"' where id =" + client.getId();
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
            String sql = "DELETE from client where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getLong("id"))
                .setFirstName(rs.getString("firstName"))
                .setLastName(rs.getString("lastName"))
                .setIdentityCardNr(rs.getString("identityCardNumber"))
                .setPersonalCardNr(rs.getString("personalCardNumber"))
                .setAddress(rs.getString("address"))
                .build();
    }
}
