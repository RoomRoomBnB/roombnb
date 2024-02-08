package com.sparta.roombnb.repository;

import com.sparta.roombnb.dto.PostResponseDto;
import com.sparta.roombnb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<List<PostResponseDto>> findAllByOrderByCreatedAtDesc();
}