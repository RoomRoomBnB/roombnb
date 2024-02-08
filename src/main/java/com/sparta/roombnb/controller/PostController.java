package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.PostRequestDto;
import com.sparta.roombnb.dto.PostResponseDto;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.security.UserDetailsImpl;
import com.sparta.roombnb.security.UserDetailslmpl;
import com.sparta.roombnb.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<CommonResponse<?>> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(requestDto, userDetails.getUser(), requestDto);
    }

    @GetMapping("/posts")
    public ResponseEntity<CommonResponse<?>> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> getPost(@PathVariable Long post_id) {
        return postService.getPost(post_id);
    }

    @PatchMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> updatePost(@PathVariable Long post_id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       return postService.updatePost(post_id, requestDto, userDetails.getUser().getId());
    }

    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> deletePost(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(post_id, userDetails.getUser().getId());
    }
}
