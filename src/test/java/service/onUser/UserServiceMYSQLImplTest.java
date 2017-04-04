package service.onUser;

import database.DBConnectionFactory;
import model.Report;
import model.Role;
import model.User;
import model.builder.ReportBuilder;
import model.builder.UserBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.report.ReportRepository;
import repository.report.ReportRepositoryMYSQLImpl;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQLImpl;
import repository.user.UserRepository;
import repository.user.UserRepositoryMYSQLImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static database.Constants.Roles.EMPLOYEE;
import static org.junit.Assert.*;

/**
 * Created by hanna on 28.03.2017.
 */
public class UserServiceMYSQLImplTest {

    private static UserService userService;
    private static RightsRolesRepository rightsRolesRepository;
    private static UserRepository userRepository;
    private static ReportRepository reportRepository;

    @BeforeClass
    public static void setupClass() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
        rightsRolesRepository = new RightsRolesRepositoryMySQLImpl(connection);
        userRepository = new UserRepositoryMYSQLImpl(connection, rightsRolesRepository);
        reportRepository =  new ReportRepositoryMYSQLImpl(connection);
        userService = new UserServiceMYSQLImpl(userRepository, reportRepository);

    }

    @Test
    public void findAll() throws Exception {

        userService.removeAll();
        assertEquals(userService.findAll().size(),0);

    }

    @Test
    public void findById() throws Exception {

        List <Role> roles = new ArrayList <>();
        userService.deleteByUsername("TestUNIQUE");
        roles.add(new Role(12L, EMPLOYEE, null));
        userService.save(new UserBuilder().setUsername("TestUNIQUE").setPassword("TEST").setRoles(roles).build());
        assertNotNull(userService.findById(userService.findByUserName("TestUNIQUE").getId()));
    }

    @Test
    public void save() throws Exception {

        List <Role> roles = new ArrayList <>();
        userService.deleteByUsername("TestUNIQUE");
        roles.add(new Role(12L, EMPLOYEE, null));
        assertTrue(userService.save(new UserBuilder().setUsername("TestUNIQUE").setPassword("TEST").setRoles(roles).build()));


    }

    @Test
    public void deleteByUsername() throws Exception {
        userService.deleteByUsername("TestUNIQUE");
        List <Role> roles = new ArrayList <>();
        roles.add(new Role(12L, EMPLOYEE, null));
        userService.save(new UserBuilder().setUsername("TestUNIQUE").setPassword("TEST").setRoles(roles).build());
        assertTrue(userService.deleteByUsername("TestUNIQUE"));
    }

    @Test
    public void update() throws Exception {

        User user = userService.findByUserName("TestUsername@test.com");
        user.setPassword("12345.sdgfd");
        assertTrue(userService.update(user));

    }

    @Test
    public void removeAll() throws Exception {
        userService.removeAll();
        assertEquals(userService.findAll().size(), 0);
    }

    @Test
    public void findByUserName() throws Exception {

        userService.deleteByUsername("TEST123");
        List <Role> roles = new ArrayList <>();
        roles.add(new Role(1L, EMPLOYEE, null));
        User user = new UserBuilder().setUsername("TEST123").setPassword("12334test").setRoles(roles).build();
        userService.save(user);
        assertNotNull(userService.findByUserName("TEST123"));
    }

    @Test
    public void findByUsernameAndPassword() throws Exception {

        List <Role> roles = new ArrayList <>();
        roles.add(new Role(1L, EMPLOYEE, null));
        User user = new UserBuilder().setUsername("TEST123").setPassword("12334test").setRoles(roles).build();
         //userService.save(user);
        assertNull(userService.findByUsernameAndPassword("TEST123", "12334test"));

    }

    @Test
    public void addReportToUser() throws Exception {

        Report report=new ReportBuilder()
                .setUser_id(2L)
                .setReport("Test Report")
                .setDate(new Date())
                .build();
        assertTrue(userService.addReportToUser(report));

    }

    @Test
    public void generateReportOfEmployee() throws Exception {

        assertNotNull(userService.generateReportOfEmployee(2L));

    }

}