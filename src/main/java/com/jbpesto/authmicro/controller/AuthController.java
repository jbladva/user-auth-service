package com.jbpesto.authmicro.controller;

import com.jbpesto.authmicro.dto.SignUpDto;
import com.jbpesto.authmicro.model.Role;
import com.jbpesto.authmicro.model.User;
import com.jbpesto.authmicro.repository.RoleRepository;
import com.jbpesto.authmicro.repository.UserRepository;
import com.jbpesto.authmicro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password) {
//        // Check if username is already taken
//        if (userService.findByUsername(username) != null) {
//            return ResponseEntity.badRequest().body("Username already exists");
//        }
//
//        // Register user
//        userService.registerUser(username, password);
//        return ResponseEntity.ok("User registered successfully");
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByUserName(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already exist!", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already exist!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUserName(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<>("User is registered successfully!", HttpStatus.OK);
    }
}

