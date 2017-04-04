package repository.user;

import database.DBConnectionFactory;
import model.Role;
import model.User;
import model.builder.UserBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQLImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Roles.EMPLOYEE;
import static org.junit.Assert.*;

/**
 * Created by hanna on 28.03.2017.
 */
public class UserRepositoryMYSQLImplTest {

    private static UserRepository userRepository;
    private static RightsRolesRepository rightsRolesRepository;


    @BeforeClass
    public static void setupClass() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
        rightsRolesRepository = new RightsRolesRepositoryMySQLImpl(connection);
        userRepository = new UserRepositoryMYSQLImpl(connection, rightsRolesRepository);

    }

    @Test
    public void findAll() throws Exception {

        //userRepository.removeAll();
        userRepository.deleteByUsername("TestUNIQUE");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(12L, EMPLOYEE, null));
        userRepository.save(new UserBuilder()
                        .setUsername("TestUNIQUE")
                        .setPassword("TEST")
                        .setRoles(roles)
                        .build());
        assertNotNull(userRepository.findAll());

    }

    @Test
    public void findById() throws Exception {

        assertNotNull(userRepository.findById(2L));
    }

    @Test
    public void save() throws Exception {

        List<Role> roles = new ArrayList<>();
        roles.add(new Role(12L, EMPLOYEE, null));
        userRepository.deleteByUsername("TestUNIQUE");
        assertTrue(
                userRepository.save(new UserBuilder()
                        .setUsername("TestUNIQUE")
                        .setPassword("TEST")
                        .setRoles(roles)
                        .build()
                ));


    }

    @Test
    public void deleteByUsername() throws Exception {

        List<Role> roles = new ArrayList<>();
        roles.add(new Role(12L, EMPLOYEE, null));
        userRepository.deleteByUsername("TestUNIQUE");
        userRepository.save(new UserBuilder()
                .setUsername("TestUNIQUE")
                .setPassword("TEST")
                .setRoles(roles)
                .build());

        assertTrue(userRepository.deleteByUsername("TestUNIQUE"));
    }

    @Test
    public void update() throws Exception {

        List<Role> roles = new ArrayList<>();
        roles.add(new Role(12L, EMPLOYEE, null));
        userRepository.deleteByUsername("TestUNIQUE");
        userRepository.save(new UserBuilder()
                .setUsername("TestUNIQUE")
                .setPassword("TEST")
                .setRoles(roles)
                .build());

        User user = userRepository.findByUserName("TestUNIQUE");
        user.setPassword("12345.sdgfd");
        assertTrue(userRepository.update(user));

    }

    @Test
    public void removeAll() throws Exception {
        userRepository.removeAll();
        assertEquals(userRepository.findAll().size(), 0);
    }

    @Test
    public void findByUserName() throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, EMPLOYEE, null));

        User user = new UserBuilder()
                .setUsername("TEST123")
                .setPassword("12334test")
                .setRoles(roles)
                .build();
        userRepository.deleteByUsername("Test123");
        userRepository.save(user);
        assertNotNull(userRepository.findByUserName("TEST123"));
    }

    @Test
    public void findByUsernameAndPassword() throws Exception {

        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, EMPLOYEE, null));
        User user = new UserBuilder()
                .setUsername("TEST123")
                .setPassword("12334test")
                .setRoles(roles)
                .build();
       // userRepository.save(user);
        assertNotNull(userRepository.findByUsernameAndPassword("TEST123", "12334test"));

    }

}