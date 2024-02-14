package com.sparta.roombnb.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageRequestDto {

    private String username;
    private String email;
    private String photo;
    private String password;
}
