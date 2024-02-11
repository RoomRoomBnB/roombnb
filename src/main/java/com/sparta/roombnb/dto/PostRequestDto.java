package com.sparta.roombnb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String contents;
    private Long rating;
    private Long room_id;
}
