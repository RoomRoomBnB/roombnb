package com.sparta.roombnb.dto;


import com.sparta.roombnb.entity.Bookmark;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkResponseDto {

    private String postTitle;
    private Long postId;

    public BookmarkResponseDto(Bookmark bookmark) {
        this.postId = bookmark.getPost().getId();
        this.postTitle = bookmark.getPost().getTitle();
    }
}
