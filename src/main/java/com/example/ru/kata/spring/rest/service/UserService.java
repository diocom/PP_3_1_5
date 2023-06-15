package com.example.ru.kata.spring.rest.service;

import com.example.ru.kata.spring.rest.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findOne(Long id);
    User findUser(String userName);
    void save(User user);
    void update(Long id, User updatedUser);
    void delete(Long id);
}
