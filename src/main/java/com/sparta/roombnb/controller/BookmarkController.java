package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.BookmarkRequestDto;
import com.sparta.roombnb.dto.BookmarkResponseDto;
import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.security.CustomUserDetails;
import com.sparta.roombnb.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Bookmark API", description = "북마크 API")
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(summary = "북마크 생성", description = "북마크 생성")
    @PostMapping
    public ResponseEntity<CommonResponse<List<BookmarkResponseDto>>> createBookmark(
        @RequestBody BookmarkRequestDto request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            List<BookmarkResponseDto> response = bookmarkService.createBookmark(request,
                userDetails.getUser());
            return ResponseEntity.ok()
                .body(CommonResponse.<List<BookmarkResponseDto>>builder()
                    .statusCode(HttpStatus.OK.value())
                    .msg("북마크가 생성되었습니다.")
                    .data(response)
                    .build());

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest()
                .body(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }

    }

    @Operation(summary = "북마크 조회", description = "북마크 조회")
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

    @Operation(summary = "북마크 삭제", description = "북마크 삭제")
    @DeleteMapping()
    public ResponseEntity<CommonResponse<?>> deleteBookmark(
        @RequestBody BookmarkRequestDto request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            bookmarkService.deleteBookmark(request, userDetails.getUser());
            return ResponseEntity.ok()
                .body(new CommonResponse<>(HttpStatus.OK.value(), "북마크가 삭제되었습니다."));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest()
                .body(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }


}
