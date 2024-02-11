package com.sparta.roombnb.entity;

import com.sparta.roombnb.dto.UserSignupRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="user")
@Entity
@Getter
@Setter
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "introduction", nullable = true)
    private String introduction;
    @Column(name = "photo", nullable = true)
    private String photo;
    @Column(name = "role", nullable = false)
    private String role;



    public User(UserSignupRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.email = requestDto.getEmail();
        this.introduction = requestDto.getIntroduction();
        this.photo = requestDto.getPhoto();
        this.role = requestDto.getRole();
    }
}