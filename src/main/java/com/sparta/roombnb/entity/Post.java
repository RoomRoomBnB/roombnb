package com.sparta.roombnb.entity;

import com.sparta.roombnb.dto.PostRequestDto;
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
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private Long rating;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Bookmark> BookmarkList = new ArrayList<>();

    public Post(PostRequestDto requestDto, User user, Room room) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.rating = requestDto.getRating();
        this.room = room;
        this.user = user;
    }

    public void update(PostRequestDto requestDto, Room room) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.room = room;
        this.rating = requestDto.getRating();
    }
}
