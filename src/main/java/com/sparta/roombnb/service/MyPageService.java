package com.sparta.roombnb.service;

import com.sparta.roombnb.config.SecurityConfig;
import com.sparta.roombnb.dto.MyPageRequestDto;
import com.sparta.roombnb.dto.MyPageResponseDto;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final SecurityConfig config;

    public MyPageResponseDto updateMyPage(MyPageRequestDto request, User user) {
        User existingUser = findUser(user.getId());

        existingUser.setEmail(request.getEmail());
        existingUser.setUsername(request.getUsername());
        existingUser.setPhoto(request.getPhoto());
        userRepository.save(existingUser);

        return new MyPageResponseDto(existingUser);
    }

    @Transactional
    public MyPageResponseDto getMyPage(User user) {
        User existingUser = findUser(user.getId());
        return new MyPageResponseDto(existingUser);
    }

    @Transactional
    public void updatePassword(MyPageRequestDto request, User user) {
        User existingUser = findUser(user.getId());
        existingUser.setPassword(config.bCryptPasswordEncoder().encode(request.getPassword()));
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RejectedExecutionException("해당 사용자가 존재하지 않습니다."));
    }
}
