package com.sparta.roombnb.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
    private String title;
    private String room;
    private Long star;
    private String contents;
    private String username;
    private LocalDateTime createdAt;
    //private List<Comment> commentList;
}
