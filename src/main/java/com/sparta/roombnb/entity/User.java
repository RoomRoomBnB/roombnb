package com.sparta.roombnb.entity;


import com.sparta.roombnb.dto.UserSignupRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name="user")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",updatable = false)
    private Long id;
    @Column(name="username", nullable = false,unique = true)
    private String username;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="email", nullable = false,unique = true)
    private String email;
    @Column(name="introduction", nullable = true)
    private String introduction;
    @Column(name="photo", nullable = true)
    private String photo;
    @Column(name="role",nullable = false)
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
