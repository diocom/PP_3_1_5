package com.example.ru.kata.spring.rest.controller;

import com.example.ru.kata.spring.rest.dataerrorhandler.ErrorInfo;
import com.example.ru.kata.spring.rest.dataerrorhandler.UserExistCheck;
import com.example.ru.kata.spring.rest.model.Role;
import com.example.ru.kata.spring.rest.model.User;
import com.example.ru.kata.spring.rest.service.RoleService;
import com.example.ru.kata.spring.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(value="/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserExistCheck userExistCheck;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService, UserExistCheck userExistCheck) {
        this.userService = userService;
        this.roleService = roleService;
        this.userExistCheck = userExistCheck;
    }

    @PostMapping()
    public ResponseEntity<User> userRegister(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        userService.save(user);
        return new ResponseEntity<>(user,headers,HttpStatus.CREATED);
    }

    /*    @PostMapping
    public ResponseEntity<HttpStatus> userRegister(@RequestBody @Valid User user, BindingResult bindingResult) {
        userExistCheck.validate(user, bindingResult);
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        } else {
            userService.save(user);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
*/
    @GetMapping("/admin")
    public ResponseEntity<List<User>> listUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/roles")
        public ResponseEntity<List<Role>> listRoles() {
        return new ResponseEntity<>(roleService.listRoles(), HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<HttpStatus> patchUser(@RequestBody User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ErrorInfo> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(new ErrorInfo("Deleted successful"), HttpStatus.OK);
    }
    @GetMapping
    public List<User> userList() {
        return userService.findAll();
    }
}
