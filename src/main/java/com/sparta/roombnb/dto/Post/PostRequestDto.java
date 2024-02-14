package com.sparta.roombnb.dto.Post;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

    private String title;
    private String contents;
    //@Size(min=0,max=5)
    @Min(value = 0, message = "aaa")
    @Max(value = 5, message = "bbb")
    private Long rating;
    private String contentId;
}
