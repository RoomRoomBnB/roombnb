package com.sparta.roombnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String room;
    @Column(nullable = false)
    private Long star;
    @Column(nullable = false)
    private String username;

//    @OneToMany
//    @JoinColumn(name="post_id")
//    private  List<Comment> commentList = new ArrayList<>();

}
