package repository.user;

import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public interface UserRepository {



    List<User> findAll();

    User findById(Long id) throws EntityNotFoundException;

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean save(User user);

    boolean deleteByUsername(String username);

    boolean update(User user);

    void removeAll();

    User findByUserName(String username);
}
