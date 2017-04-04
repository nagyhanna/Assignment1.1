package service.onUser;

import model.Role;
import model.User;
import model.validation.Notification;

import java.util.List;

/**
 * Created by hanna on 27.03.2017.
 */
public interface AuthentificationService {

    Notification<Boolean> register(String username, String password);

    Notification<User> login(String username, String password);

    boolean logout(User user);

    List<Role> getRolesOfUser(Long userId);
}
