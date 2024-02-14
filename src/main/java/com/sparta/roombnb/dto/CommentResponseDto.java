package com.sparta.roombnb.dto;

import com.sparta.roombnb.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;
    private String content;
    private LocalDateTime createDate;
    private String username;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getComment_id();
        this.content = comment.getContent();
        this.createDate = LocalDateTime.now();
        this.username = comment.getUser().getUsername();
    }
}
