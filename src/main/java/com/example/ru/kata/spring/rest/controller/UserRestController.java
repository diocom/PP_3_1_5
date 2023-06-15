package com.example.ru.kata.spring.rest.controller;

import com.example.ru.kata.spring.rest.model.User;
import com.example.ru.kata.spring.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RequestMapping("/api")
@RestController
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> showUser(Principal principal) {
        User user = userService.findUser(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
