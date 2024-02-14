package com.sparta.roombnb.dto.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    private Long postId;
    private String content;

}
