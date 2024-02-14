package com.sparta.roombnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "room")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String contentId;
    @Column(nullable = false)
    private Long postId;


    public Room(Long PostId, String contentId) {
        this.postId = PostId;
        this.contentId = contentId;
    }
}
