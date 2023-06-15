package com.example.ru.kata.spring.rest.security;

import com.example.ru.kata.spring.rest.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.ru.kata.spring.rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class ExtendedUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public ExtendedUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(("Пользователь не зарегистрирован"));
        }
        return new ExtendedUserDetails(user);
    }
}
