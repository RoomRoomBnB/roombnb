package com.sparta.roombnb.dto.MyPage;

import com.sparta.roombnb.dto.Bookmark.BookmarkResponseDto;
import com.sparta.roombnb.entity.Bookmark;
import com.sparta.roombnb.entity.User;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageResponseDto {

    private String username;
    private String email;
    private String photo;
    List<BookmarkResponseDto> bookmarkList;

    public MyPageResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.photo = user.getPhoto();
        for (Bookmark bookmark : user.getBookmark()) {
            bookmarkList.add(new BookmarkResponseDto(bookmark));
        }
    }
}
