package com.sparta.roombnb.entity;

import com.sparta.roombnb.dto.Post.PostRequestDto;
import com.sparta.roombnb.dto.RoomDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
