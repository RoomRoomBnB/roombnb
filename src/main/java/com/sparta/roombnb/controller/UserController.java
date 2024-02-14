package com.sparta.roombnb.controller;

import static com.sparta.roombnb.service.StatusCheck.success;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.Signup.UserSignupRequestDto;
import com.sparta.roombnb.dto.Signup.UserSignupResponseDto;
import com.sparta.roombnb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/api/users/signup")
    public ResponseEntity<CommonResponse<?>> singUp(
        @RequestBody UserSignupRequestDto requestDto) {
        UserSignupResponseDto userSignupResponseDto = userService.signUp(requestDto);

        return success("회원가입 성공", userSignupResponseDto);

    }
}

