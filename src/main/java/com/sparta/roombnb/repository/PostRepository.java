package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p left join fetch p.commentList")
    Optional<List<Post>> findAllByOrderByCreatedAtDesc();

    @Query("select p from Post p left join fetch p.commentList")
    Optional<List<Post>> findAllByOrderByRatingDesc();

}
