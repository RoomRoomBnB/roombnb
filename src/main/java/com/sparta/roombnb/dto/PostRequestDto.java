package com.sparta.roombnb.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String contents;
    @Size(min=0,max=5)
    private Long rating;
    private String contentId;
}
