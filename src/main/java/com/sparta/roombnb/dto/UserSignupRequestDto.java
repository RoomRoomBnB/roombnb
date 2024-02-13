package com.sparta.roombnb.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class UserSignupRequestDto {
    private String username;
    private String password;
    private String email;
    private String introduction;
    private String photo;
}
