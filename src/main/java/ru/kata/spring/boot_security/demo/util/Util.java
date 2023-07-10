package ru.kata.spring.boot_security.demo.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entityes.Role;
import ru.kata.spring.boot_security.demo.entityes.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class Util implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public Util(UserService userService, RoleService roleService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        userRoles.add(roleUser);


        User userAdmin = new User("admin", "admin", "$2y$10$08fime4hWZ5TMO.JkPEmXuIwyBchRDIbR/5QqtOnDtXE1s1LV52De", "admin@mail.ru", (byte) 24, adminRoles);
        User userUser = new User("user", "user", "$2a$12$ADWSZDGl3wk8MomYJ4F47.TQJUuePFZmiQ94HP8YsfIzXJfGBvyam", "user@mail.ru", (byte) 45, userRoles);
        System.out.println(userAdmin);
        userRepository.save(userAdmin);
        System.out.println(userUser);
        userRepository.save(userUser);


    }
}