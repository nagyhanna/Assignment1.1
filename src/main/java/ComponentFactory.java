import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMYSQLImpl;
import repository.client.ClientRepository;
import repository.client.ClientRespositoryMYSQLImpl;
import repository.report.ReportRepository;
import repository.report.ReportRepositoryMYSQLImpl;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQLImpl;
import repository.user.UserRepository;
import repository.user.UserRepositoryMYSQLImpl;
import service.onAccount.AccountService;
import service.onAccount.AccountServiceImpl;
import service.onClient.ClientService;
import service.onClient.ClientServiceImpl;
import service.onUser.AuthentificationService;
import service.onUser.AuthentificationServiceMYSQLImpl;
import service.onUser.UserService;
import service.onUser.UserServiceMYSQLImpl;

import java.sql.Connection;

/**
 * Created by hanna on 27.03.2017.
 */
public class ComponentFactory {

    private final AuthentificationService authenticationService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ReportRepository reportRepository;
    private final ClientService clientService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final ClientRepository clientRepository;

    private static ComponentFactory instance;

    public static ComponentFactory instance() {
        if (instance == null) {
            instance = new ComponentFactory();
        }
        return instance;
    }

    private ComponentFactory() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQLImpl(connection);
        this.userRepository = new UserRepositoryMYSQLImpl(connection, rightsRolesRepository);
        this.authenticationService = new AuthentificationServiceMYSQLImpl(this.userRepository, this.rightsRolesRepository);
        reportRepository = new ReportRepositoryMYSQLImpl(connection);
        userService = new UserServiceMYSQLImpl(userRepository, reportRepository);
        accountRepository = new AccountRepositoryMYSQLImpl(connection);
        accountService = new AccountServiceImpl(accountRepository);
        clientRepository = new ClientRespositoryMYSQLImpl(connection);
        clientService = new ClientServiceImpl(clientRepository);
    }

    public AuthentificationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
