package com.sparta.roombnb.dto;

import com.sparta.roombnb.entity.Bookmark;
import com.sparta.roombnb.entity.User;

import java.util.List;

public class MyPageResponseDto {
    private String username;
    private String email;
    private String photo;
    List<BookmarkResponseDto> bookmarkList; //북마크 만든 후 수정필요

    public MyPageResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getUsername();
        this.photo = user.getUsername();
//        for(Bookmark bookmark: user.getBookmark()){
//            bookmarkList.add(new BookmarkResponseDto(bookmark)); //Post entity 필요
//        }
    }
}
