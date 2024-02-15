package com.sparta.roombnb.dto.Signup;


import com.sparta.roombnb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserSignupResponseDto {

    private Long id;
    private String username;
    private String email;
    private String introduction;
    private String photo;

    public UserSignupResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.introduction = user.getIntroduction();
        this.photo = user.getPhoto();
    }

}
