package com.example.ru.kata.spring.rest.service;

import com.example.ru.kata.spring.rest.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleName(String userRoleName);
    List<Role> listRoles();

}
