package com.jbpesto.authmicro.service;

import com.jbpesto.authmicro.model.User;

public interface UserService {
    User registerUser(String username, String password);
    User findByUsername(String username);
}

