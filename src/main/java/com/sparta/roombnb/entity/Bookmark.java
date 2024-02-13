package com.sparta.roombnb.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false, unique = true) //post는 user당 1개씩만 unique로 저장
    private Post post;

    public Bookmark(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public Bookmark(Bookmark bookmark) {
        this.id = bookmark.getId();
        this.user = bookmark.getUser();
        this.post = bookmark.getPost();
    }
}
