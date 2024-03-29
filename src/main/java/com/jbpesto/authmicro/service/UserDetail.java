package com.jbpesto.authmicro.service;

import com.jbpesto.authmicro.model.User;
import com.jbpesto.authmicro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetail implements UserDetailsService {

    UserRepository userRepo;

    @Autowired
    UserDetail(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByUserNameOrEmail(username, username);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not exists by Username");
        }

        User user = userOptional.get();
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }
}
