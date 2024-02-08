package com.sparta.roombnb.entity;

import com.sparta.roombnb.dto.PostRequestDto;
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
    private String room_name;
    @Column(nullable = false)
    private Long rating;
    @Column(nullable = false)
    private String username;

//    @OneToMany
//    @JoinColumn(name="post_id")
//    private  List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto requestDto,User user,Room room){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.room_name = room.getName();
        this.rating = requestDto.getRating();
        this.username = user.getUsername();
    }

    public void update(PostRequestDto requestDto,Room room) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.room_name = room.getName();
        this.rating = requestDto.getRating();
    }
}
