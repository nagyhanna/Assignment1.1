package service.onUser;

import database.DBConnectionFactory;
import model.User;
import model.validation.ResultFetchException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQLImpl;
import repository.user.UserRepository;
import repository.user.UserRepositoryMYSQLImpl;

import java.sql.Connection;

/**
 * Created by hanna on 28.03.2017.
 */
public class AuthentificationServiceMYSQLImplTest { private static AuthentificationService authenticationService;
    private static UserRepository userRepository;

    @BeforeClass
    public static void setUp() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQLImpl(connection);
        userRepository = new UserRepositoryMYSQLImpl(connection, rightsRolesRepository);

        authenticationService = new AuthentificationServiceMYSQLImpl(
                userRepository,
                rightsRolesRepository
        );
    }

    @Test
    public void register() throws Exception, ResultFetchException {
        Assert.assertTrue(
                authenticationService.register("Test@test.com", "Test123.6").getResult()
        );
    }

    @Test
    public void login() throws Exception, ResultFetchException {
        String username = "Test2@test.com";
        String password = "Test123.6";
        authenticationService.register(username, password);

        User user = authenticationService.login(username, password).getResult();

        Assert.assertNotNull(user);
    }

    @Test
    public void logout() throws Exception {

    }

}