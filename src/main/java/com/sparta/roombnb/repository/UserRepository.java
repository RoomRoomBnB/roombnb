package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}