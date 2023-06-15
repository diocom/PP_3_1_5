package com.example.ru.kata.spring.rest.dataerrorhandler;

import com.example.ru.kata.spring.rest.model.User;
import com.example.ru.kata.spring.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserExistCheck implements Validator {
    private final UserService userService;

    @Autowired
    public UserExistCheck(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userService.findUser(user.getUserName());
        } catch (UsernameNotFoundException i) {
            return;
        }
        errors.rejectValue("userName","Имя пользователя недоступно");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
}
