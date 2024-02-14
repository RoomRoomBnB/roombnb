package com.sparta.roombnb.entity;

import com.sparta.roombnb.dto.PostRequestDto;
import com.sparta.roombnb.dto.RoomDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.Delete;

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
    @Column(nullable = false)
    private String room_title;
    @Column(nullable = false)
    private String room_id;
    @Column(nullable = false)
    private String room_tel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post")

    private List<Bookmark> BookmarkList = new ArrayList<>();

    public Post(PostRequestDto requestDto, User user, RoomDto roomDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.rating = requestDto.getRating();
        this.user = user;
        this.room_title = roomDto.getTitle();
        this.room_tel = roomDto.getTel();
        this.room_id = roomDto.getContentId();
    }

    public void update(PostRequestDto requestDto, RoomDto roomDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.rating = requestDto.getRating();
        this.room_title = roomDto.getTitle();
        this.room_id = roomDto.getContentId();
    }
}
