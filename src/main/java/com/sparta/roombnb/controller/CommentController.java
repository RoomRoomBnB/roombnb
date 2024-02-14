package com.sparta.roombnb.controller;

import static com.sparta.roombnb.service.StatusCheck.badRequest;
import static com.sparta.roombnb.service.StatusCheck.success;

import com.sparta.roombnb.dto.Comment.CommentRequestDto;
import com.sparta.roombnb.dto.Comment.CommentResponseDto;
import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.security.CustomUserDetails;
import com.sparta.roombnb.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comment API", description = "댓글 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "댓글 생성")
    @PostMapping("")
    public ResponseEntity<CommonResponse<?>> createComment(
        @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal
    CustomUserDetails userDetails) {
        try {
            CommentResponseDto responseDto = commentService.createComment(requestDto,
                userDetails.getUser());
            return success("댓글이 성공적으로 생성되었습니다.", responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return badRequest(ex.getMessage());
        }
    }

    @Operation(summary = "댓글 수정", description = "댓글 수정")
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommonResponse<?>> updateComment(@PathVariable Long commentId,
        @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal
    CustomUserDetails userDetails) {
        try {
            CommentResponseDto responseDto = commentService.updateComment(commentId, requestDto,
                userDetails.getUser());

            return success("댓글이 성공적으로 수정되었습니다.", responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return badRequest(ex.getMessage());
        }
    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponse<?>> deleteComment(
        @PathVariable Long commentId, @AuthenticationPrincipal
    CustomUserDetails userDetails) {
        try {
            commentService.deleteComment(commentId, userDetails.getUser());
            return success("댓글이 성공적으로 삭제되었습니다.", null);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return badRequest(ex.getMessage());
        }
    }
}
