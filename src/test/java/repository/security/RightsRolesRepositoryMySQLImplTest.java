package repository.security;

import database.DBConnectionFactory;
import model.Role;
import model.User;
import model.builder.UserBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.user.UserRepository;
import repository.user.UserRepositoryMYSQLImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Roles.EMPLOYEE;
import static org.junit.Assert.*;

/**
 * Created by hanna on 28.03.2017.
 */
public class RightsRolesRepositoryMySQLImplTest {

    private static RightsRolesRepository rightsRolesRepository;
    private static UserRepository userRepository;

    @BeforeClass
    public static void setupClass() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
        rightsRolesRepository = new RightsRolesRepositoryMySQLImpl(connection);
        userRepository = new UserRepositoryMYSQLImpl(connection, rightsRolesRepository);


    }

    @Test
    public void addRole() throws Exception {
        assertTrue(rightsRolesRepository.addRole("AdminTest"));
    }

    @Test
    public void addRight() throws Exception {
        assertTrue(rightsRolesRepository.addRight("AdminTest"));
    }

    @Test
    public void findRoleByTitle() throws Exception {
        assertNotNull(rightsRolesRepository.findRoleByTitle("AdminTest"));
    }

    @Test
    public void findRoleById() throws Exception {
        assertNotNull(rightsRolesRepository.findRoleById(1L));
    }

    @Test
    public void findRightByTitle() throws Exception {
        assertNotNull(rightsRolesRepository.findRightByTitle("AdminTest"));
    }

    @Test
    public void addRolesToUser() throws Exception {

        User user =  new UserBuilder().setUsername("Test2").setPassword("Test2").build();
        //userRepository.save(user);
        List<Role> roles = new ArrayList<>();
        roles.add(rightsRolesRepository.findRoleByTitle(EMPLOYEE));
        assertTrue(rightsRolesRepository.addRolesToUser(userRepository.findByUserName("Test2"), roles));
    }

    @Test
    public void findRolesForUser() throws Exception {

        assertEquals(rightsRolesRepository.findRolesForUser(userRepository.findByUserName("Test2").getId()).size(), 1);
    }

}