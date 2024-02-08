package com.sparta.roombnb.repository;

import com.sparta.roombnb.dto.PostResponseDto;
import com.sparta.roombnb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<PostResponseDto> findAllByOrderByCreatedAtDesc();
}
