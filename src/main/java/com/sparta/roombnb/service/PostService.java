package com.sparta.roombnb.service;

import com.sparta.roombnb.dto.PostRequestDto;
import com.sparta.roombnb.dto.PostResponseDto;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.PostRepository;
import com.sparta.roombnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Room room = roomRepository.findById(requestDto.getRoom_id());
        Post post = postRepository.save(new Post(requestDto, user, room));
        return new PostResponseDto(post);
    }

}
