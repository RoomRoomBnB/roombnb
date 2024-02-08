package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.CommentRequestDto;
import com.sparta.roombnb.dto.CommentResponseDto;
import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.security.UserDetailsImpl;
import com.sparta.roombnb.security.UserDetailslmpl;
import com.sparta.roombnb.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public CommonResponse<CommentResponseDto> createComment(
        @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {

        CommentResponseDto responseDto = commentService.createComment(requestDto,
            userDetails.getUser());

        CommonResponse<CommentResponseDto> response = CommonResponse.<CommentResponseDto>builder()
            .statusCode(200)
            .msg("댓글이 성공적으로 생성되었습니다")
            .data(responseDto)
            .build();

        return response;
    }

    @PatchMapping("/{commentId}")
    public CommonResponse<CommentResponseDto> updateComment(@PathVariable Long commentId,
        @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal
                                                                UserDetailsImpl userDetails) {

        CommentResponseDto responseDto = commentService.updateComment(commentId, requestDto,
            userDetails.getUser());

        CommonResponse<CommentResponseDto> response = CommonResponse.<CommentResponseDto>builder()
            .statusCode(200)
            .msg("댓글이 성공적으로 수정되었습니다")
            .data(responseDto)
            .build();

        return response;
    }

    @DeleteMapping("/{commentId}")
    public CommonResponse<CommentResponseDto> deleteComment(
        @PathVariable Long commentId, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        CommonResponse<CommentResponseDto> response = CommonResponse.<CommentResponseDto>builder()
            .statusCode(200)
            .msg("댓글이 성공적으로 삭제되었습니다")
            .build();

        return response;

    }


}
