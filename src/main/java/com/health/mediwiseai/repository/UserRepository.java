package com.health.mediwiseai.repository;

import com.health.mediwiseai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // This method will find a user by email
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUsername(String username);

    // To check if a user already exists
    boolean existsByUsername(String username);
}
