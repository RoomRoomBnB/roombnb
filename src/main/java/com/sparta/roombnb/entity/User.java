package com.sparta.roombnb.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String introduction;
    @Column(nullable = false)
    private String photo;

    @OneToMany(mappedBy = "bookmark")
    private List<Bookmark> bookmark = new ArrayList<>();

    public User(String username, String password, String email, String introduction, String photo) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduction = introduction;
        this.photo = photo;


    }
}
