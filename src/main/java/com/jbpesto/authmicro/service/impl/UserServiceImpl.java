package com.jbpesto.authmicro.service.impl;

import com.jbpesto.authmicro.model.User;
import com.jbpesto.authmicro.repository.UserRepository;
import com.jbpesto.authmicro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password)); // Encrypt password
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserNameOrEmail(username,username).orElseThrow(() -> new UsernameNotFoundException("User name not found"));
    }
}

