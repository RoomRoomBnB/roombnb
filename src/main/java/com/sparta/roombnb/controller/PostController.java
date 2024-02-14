package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.PostRequestDto;
import com.sparta.roombnb.security.CustomUserDetails;
import com.sparta.roombnb.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Post API", description = "게시글 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 작성", description = "게시글 작성")
    @PostMapping("/posts")
    public ResponseEntity<CommonResponse<?>> createPost(
        @Valid @RequestBody PostRequestDto requestDto,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return postService.createPost(requestDto, userDetails.getUser());
    }

    // 작성일 기준 내림차순
    @Operation(summary = "게시글 전체 조회", description = "게시글 전체 조회 - 작성일순")
    @GetMapping("/posts")
    public ResponseEntity<CommonResponse<?>> getAllPost() {
        return postService.getAllPost();
    }


    @Operation(summary = "특정 게시글 조회", description = "특정 게시글 조회")
    @GetMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> getPost(@PathVariable Long post_id) {
        return postService.getPost(post_id);
    }

    //게시글 전체 조회 기능 - 별점 높은 순
    @Operation(summary = "게시글 전체 조회", description = "게시글 전체 조회 - 별점순")
    @GetMapping("/posts/order-rating")
    public ResponseEntity<CommonResponse<?>> getAllPostOrderRating() {
        return postService.getAllPostOrderRating();
    }

    //게시글 수정 기능 - 자신이 쓴 게시글만 가능
    @Operation(summary = "게시글 수정", description = "게시글 수정")
    @PatchMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> updatePost(@PathVariable Long post_id,
        @Valid @RequestBody PostRequestDto requestDto,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return postService.updatePost(post_id, requestDto, userDetails.getUser().getId());
    }

    //게시글 삭제 기능 - 자신이 쓴 게시글만 가능
    @Operation(summary = "게시글 삭제", description = "게시글 삭제")
    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> deletePost(@PathVariable Long post_id,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return postService.deletePost(post_id, userDetails.getUser().getId());
    }
}
