package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}

