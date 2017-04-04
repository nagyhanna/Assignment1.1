package repository.user;

import model.Client;
import model.User;
import model.validation.Notification;
import model.builder.UserBuilder;
import repository.EntityNotFoundException;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;

/**
 * Created by hanna on 26.03.2017.
 */
public class UserRepositoryMYSQLImpl implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;

    public UserRepositoryMYSQLImpl(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getUserFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)");
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByUsername(String username) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where username ='" + username +"'";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE user SET username = '" + user.getUsername() +
                    "', password= '"+ user.getPassword()+"' where id =" + user.getId();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUserName(String username) {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user where username='" + username + "'";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();

        try {
            Statement statement = connection.createStatement();

            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            userResultSet.next();

            User user = new UserBuilder()
                    .setUsername(userResultSet.getString("username"))
                    .setPassword(userResultSet.getString("password"))
                    .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                    .build();
            findByUsernameAndPasswordNotification.setResult(user);
            return findByUsernameAndPasswordNotification;
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Invalid email or password!");
            return findByUsernameAndPasswordNotification;
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new UserBuilder()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString("password"))
                .build();
    }
}
