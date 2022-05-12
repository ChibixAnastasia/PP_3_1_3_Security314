package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByRoleName(String role) {
        return roleRepository.getRoleByRoleName(role);
    }

    @Override
    public List<Role> getListOfRoles(String[] roleNames) {
        List<Role> roleList = new ArrayList<>();
        for(String role : roleNames){
            roleList.add(getRoleByRoleName(role));
        }
        return roleList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
