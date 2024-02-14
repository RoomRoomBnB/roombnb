package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<List<Post>> findAllByOrderByCreatedAtDesc();

    Optional<List<Post>> findAllByOrderByRatingDesc();

}
