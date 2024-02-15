package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
