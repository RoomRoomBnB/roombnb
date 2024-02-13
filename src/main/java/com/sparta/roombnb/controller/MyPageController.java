package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.MyPageRequestDto;
import com.sparta.roombnb.dto.MyPageResponseDto;
import com.sparta.roombnb.security.CustomUserDetails;
import com.sparta.roombnb.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.RejectedExecutionException;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/mypage")
    public ResponseEntity<CommonResponse<MyPageResponseDto>> getMyPage(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        try {
            MyPageResponseDto response = myPageService.getMyPage(userDetails.getUser());
            return ResponseEntity.ok()
                    .body(CommonResponse.<MyPageResponseDto>builder()
                            .statusCode(HttpStatus.OK.value())
                            .msg("마이페이지가 조회되었습니다.")
                            .data(response)
                            .build());
        } catch (RejectedExecutionException ex) {
            return ResponseEntity.badRequest()
                    .body(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @PatchMapping("/mypage")
    public ResponseEntity<CommonResponse<MyPageResponseDto>> updateMyPage(
            @RequestBody MyPageRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        try {
            MyPageResponseDto response = myPageService.updateMyPage(requestDto, userDetails.getUser());
            return ResponseEntity.ok()
                    .body(CommonResponse.<MyPageResponseDto>builder()
                            .statusCode(HttpStatus.OK.value())
                            .msg("마이페이지가 조회되었습니다.")
                            .data(response)
                            .build());
        } catch (RejectedExecutionException ex) {
            return ResponseEntity.badRequest()
                    .body(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @PatchMapping("/password-patch")
    public ResponseEntity<CommonResponse<?>> updatePassword(
            @RequestBody MyPageRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            myPageService.updatePassword(requestDto, userDetails.getUser());
            return ResponseEntity.ok()
                    .body(new CommonResponse<>(HttpStatus.OK.value(), "비밀번호가 수정되었습니다."));

        } catch (RejectedExecutionException ex) {
            return ResponseEntity.badRequest()
                    .body(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }

    }
}
