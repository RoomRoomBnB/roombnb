package com.sparta.roombnb.service;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.PostResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StatusCheck {
    static ResponseEntity<CommonResponse<?>> success(String msg, Object object) {
        return ResponseEntity.ok(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg(msg)
                .data(object)
                .build());
    }

    static ResponseEntity<CommonResponse<?>> badRequest(String msg) {
        return org.springframework.http.ResponseEntity.badRequest().body(CommonResponse.<PostResponseDto>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .msg(msg)
                .data(null)
                .build());
    }

    static ResponseEntity<CommonResponse<?>> forBidden(String msg) {
        return ResponseEntity.status(403).body(CommonResponse.<PostResponseDto>builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .msg(msg)
                .data(null)
                .build());
    }


}
