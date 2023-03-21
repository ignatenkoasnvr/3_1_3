package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entityes.Role;
import ru.kata.spring.boot_security.demo.entityes.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImp;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Util {
    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;

    @Autowired
    public Util(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @PostConstruct
    private void createRoleAndUser() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleServiceImp.saveRoles(adminRole);
        roleServiceImp.saveRoles(userRole);

        User admin1 = new User("Petr", "petr", "admin1@ya.ru", Set.of(adminRole));
        User admin2 = new User("Ivan", "ivan", "admin2@ya.ru", Set.of(adminRole));

        User user1 = new User("Oleg", "oleg", "user1@ya.ru", Set.of(userRole));
        User user2 = new User("Sergey", "sergey", "user2@ya.ru", Set.of(userRole));
        User user3 = new User("Elena", "elena", "user3@ya.ru", Set.of(userRole));

        userServiceImp.saveUser(admin1);
        userServiceImp.saveUser(admin2);
        userServiceImp.saveUser(user1);
        userServiceImp.saveUser(user2);
        userServiceImp.saveUser(user3);

    }

}
