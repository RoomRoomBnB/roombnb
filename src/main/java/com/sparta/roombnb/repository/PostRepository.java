package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
