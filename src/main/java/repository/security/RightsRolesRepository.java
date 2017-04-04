package repository.security;

import model.Role;
import model.Right;
import model.User;

import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public interface RightsRolesRepository {

    boolean addRole(String role);

    boolean addRight(String right);

    Role findRoleByTitle(String role);

    Role findRoleById(Long roleId);

    Right findRightByTitle(String right);

    boolean addRolesToUser(User user, List<Role> roles);

    List<Role> findRolesForUser(Long userId);

    boolean addRoleRight(Long roleId, Long rightId);
}
