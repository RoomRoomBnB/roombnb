package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.BookmarkRequestDto;
import com.sparta.roombnb.dto.BookmarkResponseDto;
import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.MyPageResponseDto;
import com.sparta.roombnb.security.CustomUserDetails;
import com.sparta.roombnb.security.UserDetailsImpl;
import com.sparta.roombnb.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<CommonResponse<List<BookmarkResponseDto>>> createBookmark(
            @RequestBody BookmarkRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        try{
            List<BookmarkResponseDto> response = bookmarkService.createBookmark(request, userDetails.getUser());
            return ResponseEntity.ok()
                    .body(CommonResponse.<List<BookmarkResponseDto>>builder()
                            .statusCode(HttpStatus.OK.value())
                            .msg("북마크가 생성되었습니다.")
                            .data(response)
                            .build());

        }catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }

    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<BookmarkResponseDto>>> getBookmark(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
            List<BookmarkResponseDto> response = bookmarkService.getBookmark(userDetails.getUser());
            return ResponseEntity.ok()
                    .body(CommonResponse.<List<BookmarkResponseDto>>builder()
                            .statusCode(HttpStatus.OK.value())
                            .msg("북마크가 조회되었습니다.")
                            .data(response)
                            .build());
    }

    @DeleteMapping()
    public ResponseEntity<CommonResponse<?>> deleteBookmark(
            @RequestBody BookmarkRequestDto request,
    @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            bookmarkService.deleteBookmark(request,userDetails.getUser());
            return ResponseEntity.ok().body(new CommonResponse<>(HttpStatus.OK.value(), "북마크가 삭제되었습니다."));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }



}
