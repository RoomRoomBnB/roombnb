package com.sparta.roombnb.dto;


import com.sparta.roombnb.entity.Bookmark;

public class BookmarkResponseDto {
    private String postTitle;
    private Long postId;

    public BookmarkResponseDto(Bookmark bookmark) {
        this.postId = bookmark.getPost().getId(); // post entity 필요
        this.postTitle = bookmark.getPost().getTitle();
    }
}
