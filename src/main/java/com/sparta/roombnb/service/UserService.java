package com.sparta.roombnb.service;


import com.sparta.roombnb.dto.UserSignupRequestDto;
import com.sparta.roombnb.dto.UserSignupResponseDto;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserSignupResponseDto signUp(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }
        requestDto.setPassword(bCryptPasswordEncoder.encode(password));
        User user = new User(requestDto);
        User saveUser = userRepository.save(user);
        return new UserSignupResponseDto(saveUser);
    }


}




