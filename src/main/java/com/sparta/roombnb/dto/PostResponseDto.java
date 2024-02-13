package com.sparta.roombnb.dto;

import com.sparta.roombnb.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private String title;
    private String room_title;
    private Long rating;
    private String contents;
    private String username;
    private String room_id;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentResponseDto;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.room_title = post.getRoom_title();
        this.rating = post.getRating();
        this.contents = post.getContents();
        this.room_id = post.getRoom_id();
        this.username = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
        this.commentResponseDto = post.getCommentList().stream().map(CommentResponseDto::new).toList();
    }
}
