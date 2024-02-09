package com.sparta.roombnb.dto;


import com.sparta.roombnb.entity.Bookmark;

public class BookmarkResponseDto {
    private String postTitle;
    private Long postId;


    public BookmarkResponseDto(Bookmark bookmark) {
        this.postId = bookmark.getPost().getId();
        this.postTitle = bookmark.getPost().getTitle();
    }
}
