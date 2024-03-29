package com.sparta.roombnb.controller;

import static com.sparta.roombnb.service.StatusCheck.badRequest;
import static com.sparta.roombnb.service.StatusCheck.success;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.MyPage.MyPageRequestDto;
import com.sparta.roombnb.dto.MyPage.MyPageResponseDto;
import com.sparta.roombnb.security.CustomUserDetails;
import com.sparta.roombnb.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.concurrent.RejectedExecutionException;
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

@Tag(name = "MyPage API", description = "마이페이지 API")
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {

    private final MyPageService myPageService;

    @Operation(summary = "마이페이지 조회", description = "마이페이지 조회")
    @GetMapping("/mypage")
    public ResponseEntity<CommonResponse<?>> getMyPage(
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        try {
            MyPageResponseDto response = myPageService.getMyPage(userDetails.getUser());
            return success("마이페이지가 조회되었습니다.", response);
        } catch (RejectedExecutionException ex) {
            return badRequest(ex.getMessage());
        }
    }

    @Operation(summary = "마이페이지 수정", description = "마이페이지 수정")
    @PatchMapping("/mypage")
    public ResponseEntity<CommonResponse<?>> updateMyPage(
        @RequestBody MyPageRequestDto requestDto,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        try {
            MyPageResponseDto response = myPageService.updateMyPage(requestDto,
                userDetails.getUser());
            return success("마이페이지가 수정되었습니다.", response);
        } catch (RejectedExecutionException ex) {
            return badRequest(ex.getMessage());
        }
    }

    @Operation(summary = "비밀번호 수정", description = "비밀번호 수정")
    @PatchMapping("/password-patch")
    public ResponseEntity<CommonResponse<?>> updatePassword(
        @RequestBody MyPageRequestDto requestDto,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            myPageService.updatePassword(requestDto, userDetails.getUser());
            return ResponseEntity.ok()
                .body(new CommonResponse<>(HttpStatus.OK.value(), "비밀번호가 수정되었습니다."));

        } catch (RejectedExecutionException ex) {
            return badRequest(ex.getMessage());
        }

    }
}
