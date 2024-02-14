package com.sparta.roombnb.dto.Post;

import com.sparta.roombnb.dto.Comment.CommentResponseDto;
import com.sparta.roombnb.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {

    private String title;
    private String room_title;
    private Long rating;
    private String contents;
    private String username;
    private String room_id;
    private String room_tel;
    private String room_addr;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentResponseDto;


    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.room_title = post.getRoom_title();
        this.room_addr = post.getRoom_address();
        this.rating = post.getRating();
        this.contents = post.getContents();
        this.room_id = post.getRoom_id();
        this.room_tel = post.getRoom_tel();
        this.username = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
        this.commentResponseDto = post.getCommentList().stream().map(CommentResponseDto::new)
            .toList();
    }
}
