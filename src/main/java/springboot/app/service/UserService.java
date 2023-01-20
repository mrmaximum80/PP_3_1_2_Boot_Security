package springboot.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springboot.app.model.Role;
import springboot.app.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUser(long id);

    void saveUser(User user);

    void deleteUser(long id);

    List<Role> getAllRoles();

    Role getRole(long id);

    void saveRole(Role role);

    void deleteRole(long id);

    User getUserByUsername(String username);

    default void defaultUsers() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        saveRole(user);
        saveRole(admin);

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(user);

        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(user);
        adminRoles.add(admin);


        saveUser(new User("Иван", "Иванов", 20, "admin", "admin", adminRoles));
        saveUser(new User("Петр", "Петров", 30, "user2", "user2", userRoles));
        saveUser(new User("Семен", "Семенов", 40, "user3", "user3", userRoles));
        saveUser(new User("Василий", "Васильев", 50, "user4", "user4", userRoles));
        saveUser(new User("Сергей", "Сергеев", 60, "user5", "user5", userRoles));

    }
}
