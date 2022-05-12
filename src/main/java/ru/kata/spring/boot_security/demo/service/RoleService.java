package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.HashSet;
import java.util.List;

@Service
public interface RoleService {
    Role addRole(Role role);
    Role getRoleByRoleName(String role);
    List<Role> getListOfRoles(String[] roleNames);
    List<Role> getAllRoles();
}
