package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Bookmark;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByPost(Post post);
    List<Bookmark> findByUser(User user);

    void deleteByUserIdAndPostId(Long id, Long postId);
}
