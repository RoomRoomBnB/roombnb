package com.sparta.roombnb.dto;

import com.sparta.roombnb.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto extends CommonResponse {

    private Long id;
    private String content;
    private UserDto user;
    private LocalDateTime createDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getComment_id();
        this.content = comment.getContent();
        this.user = new UserDto(comment.getUser());
        this.createDate = LocalDateTime.now();
    }
}
