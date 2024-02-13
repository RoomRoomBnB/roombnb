package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.UserSignupRequestDto;
import com.sparta.roombnb.dto.UserSignupResponseDto;
import com.sparta.roombnb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    @GetMapping("/test")
    public String afterLogin(){
        return "안녕";
    }

    @PostMapping("api/users/signup")
    public ResponseEntity<CommonResponse<UserSignupResponseDto>> singUp(
        @RequestBody UserSignupRequestDto requestDto) {
        UserSignupResponseDto userSignupResponseDto = userService.signUp(requestDto);
        CommonResponse<UserSignupResponseDto> response = CommonResponse.<UserSignupResponseDto>builder()
            .statusCode(HttpStatus.OK.value())
            .msg("회원가입 성공")
            .data(userSignupResponseDto)
            .build();
        return ResponseEntity.ok(response);
    }
}

