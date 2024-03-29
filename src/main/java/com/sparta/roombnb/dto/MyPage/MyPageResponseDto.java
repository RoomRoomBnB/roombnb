package com.sparta.roombnb.dto.MyPage;

import com.sparta.roombnb.dto.Bookmark.BookmarkResponseDto;
import com.sparta.roombnb.entity.Bookmark;
import com.sparta.roombnb.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MyPageResponseDto {
    private String username;
    private String email;
    private String photo;
    List<BookmarkResponseDto> bookmarkList= new ArrayList<>();; //북마크 만든 후 수정필요

    public MyPageResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.photo = user.getPhoto();
        for (Bookmark bookmark : user.getBookmark()) {
            bookmarkList.add(new BookmarkResponseDto(bookmark));
        }
    }
}
