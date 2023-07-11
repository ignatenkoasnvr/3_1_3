package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entityes.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    public User findByEmail(String username);

    public List<User> getAllUsers();

    public void saveUser(User user);

    public void deleteUser(Long id);

    public void updateUser(User user);

    public Optional<User> findById(Long id);

}