package com.sparta.roombnb.service;


import com.sparta.roombnb.dto.UserSignupRequestDto;
import com.sparta.roombnb.dto.UserSignupResponseDto;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        requestDto.setRole("ROLE_USER");
        User user = new User(requestDto);
        User saveUser = userRepository.save(user);
        return new UserSignupResponseDto(saveUser);
    }

//    public LoginResponseDto login(LoginRequestDto requestDto) {
//        Optional<User> checkEmail = userRepository.findByEmail(requestDto.getEmail());
//        if (!checkEmail.isPresent()) {
//            throw new IllegalArgumentException("가입되지 않은 E-Mail 입니다.");
//        }
//        User user = checkEmail.get();
//        // 비밀번호 검증 (비밀번호 암호화 처리를 가정하고 작성)
//        if (!requestDto.getPassword().equals(user.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//        LoginResponseDto responseDto = new LoginResponseDto(user.getUsername(), user.getEmail());
//        return responseDto;
//    }t/post

}




