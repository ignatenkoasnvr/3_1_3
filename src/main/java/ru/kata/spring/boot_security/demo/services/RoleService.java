package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entityes.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRoles();

    public void saveRoles(Role role);
}
