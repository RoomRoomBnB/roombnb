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
    private String room_name;
    private Long rating;
    private String contents;
    private String username;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentResponseDto;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.room_name = post.getRoom().getName();
        this.rating = post.getRating();
        this.contents = post.getContents();
        this.username = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
        this.commentResponseDto = post.getCommentList().stream().map(CommentResponseDto::new).toList();
    }
}
