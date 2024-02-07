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
    public ResponseEntity<CommonResponse<PostResponseDto>> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        PostResponseDto responseDto = postService.createPost(requestDto,userDetails.getUser(),requestDto);
        return ResponseEntity.ok(CommonResponse.<PostResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("게시글 작성에 성공하셨습니다.")
                .data(responseDto)
                .build());
    }

    @GetMapping("/posts")
    public ResponseEntity<CommonResponse<List<PostResponseDto>>> getAllPost(){
        List<PostResponseDto> responseDto = postService.getAllPost();
        return ResponseEntity.ok(CommonResponse.<List<PostResponseDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("전체 게시글 조회")
                .data(responseDto)
                .build());
    }

    @GetMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<PostResponseDto>> getPost(@PathVariable Long post_id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        PostResponseDto responseDto = postService.getPost(post_id,userDetails.getUser());
        return ResponseEntity.ok(CommonResponse.<PostResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("게시글 조회")
                .data(responseDto)
                .build());
    }
    @PatchMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<PostResponseDto>> updatePost(@PathVariable Long post_id,@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        PostResponseDto responseDto = postService.updatePost(post_id,requestDto,userDetails.getUser());
        return ResponseEntity.ok(CommonResponse.<PostResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("게시글을 삭제하셨습니다.")
                .data(responseDto)
                .build());
    }

    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<CommonResponse<?>> deletePost(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.deletePost(post_id,userDetails.getUser());
        return ResponseEntity.ok(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("게시글을 삭제하셨습니다.")
                .build());
    }
}
