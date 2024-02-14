package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Bookmark;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("select b from Bookmark b join fetch b.post")
    Optional<Bookmark> findByPost(Post post);
    @Query("SELECT b FROM Bookmark b JOIN FETCH b.post p WHERE b.user = :user")
    List<Bookmark> findByUser(User user);

    void deleteByUserIdAndPostId(Long id, Long postId);
}
