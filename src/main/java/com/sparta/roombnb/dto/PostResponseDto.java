package com.sparta.roombnb.dto;

import com.sparta.roombnb.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
    private String title;
    private String room_name;
    private Long rating;
    private String contents;
    private String username;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.room_name = post.getRoom().getName();
        this.rating = post.getRating();
        this.contents = post.getContents();
        this.username = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
    }
    //private List<Comment> commentList;
}
