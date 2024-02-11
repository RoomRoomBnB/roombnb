package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.PostRequestDto;
import com.sparta.roombnb.security.UserDetailsImpl;
import com.sparta.roombnb.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    //게시글 작성 기능
    @PostMapping("/posts")
    public ResponseEntity<CommonResponse<?>> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(requestDto, userDetails.getUser());
    }

    //게시글 전체 조회 기능 - 작성일 기준
    @GetMapping("/posts")
    public ResponseEntity<CommonResponse<?>> getAllPost() {
        return postService.getAllPost();
    }

    //특정 게시글 찾는 기능
    @GetMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> getPost(@PathVariable Long post_id) {
        return postService.getPost(post_id);
    }

    //게시글 전체 조회 기능 - 별점 높은 순
    @GetMapping("/posts/order-rating")
    public ResponseEntity<CommonResponse<?>> getAllPostOrderRating() {
        return postService.getAllPostOrderRating();
    }


    //게시글 수정 기능 - 자신이 쓴 게시글만 가능
    @PatchMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> updatePost(@PathVariable Long post_id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(post_id, requestDto, userDetails.getUser().getId());
    }

    //게시글 삭제 기능 - 자신이 쓴 게시글만 가능
    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> deletePost(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(post_id, userDetails.getUser().getId());
    }
}
