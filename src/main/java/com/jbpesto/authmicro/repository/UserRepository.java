package com.jbpesto.authmicro.repository;

import com.jbpesto.authmicro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameOrEmail(String username, String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

}

