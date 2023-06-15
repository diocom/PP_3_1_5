package com.example.ru.kata.spring.rest.service;

import com.example.ru.kata.spring.rest.model.Role;
import com.example.ru.kata.spring.rest.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Role getRoleName(String userRoleName) {
        return roleRepository.findByName(userRoleName);
    }
    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

}
