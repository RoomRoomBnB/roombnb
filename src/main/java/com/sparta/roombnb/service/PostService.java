package com.sparta.roombnb.service;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.PostRequestDto;
import com.sparta.roombnb.dto.PostResponseDto;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.PostRepository;
import com.sparta.roombnb.repository.UserRepository;
import com.sparta.roombnb.security.UserDetailsImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public ResponseEntity<CommonResponse<?>> createPost(PostRequestDto requestDto, User user) {
        Room room = roomRepository.findById(requestDto.getRoom_id()).orElse(null);
        if (room == null) {
            return badRequest("해당하는 숙소정보가 없습니다.");
        }
        Post post = new Post(requestDto, user, room);
        postRepository.save(post);
        return success("게시글 작성에 성공하셨습니다.",post);
    }


    public ResponseEntity<CommonResponse<?>> getAllPost() {
        List<PostResponseDto> postList = postRepository.findAllByOrderByCreatedAtDesc();
        if (postList == null) {
            return badRequest("현재 작성된 포스트가 없습니다.");
        }
        return ResponseEntity.ok(CommonResponse.<List<PostResponseDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("전체 게시글 조회")
                .data(postList)
                .build());
    }


    public ResponseEntity<CommonResponse<?>> getPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return badRequest("해당하는 포스트가 없습니다.");
        }
        return success("게시글 조회에 성공하셨습니다.",post);
    }



    public ResponseEntity<CommonResponse<?>> updatePost(Long postId, PostRequestDto requestDto, Long userId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return badRequest("해당하는 포스트가 없습니다.");
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return badRequest("해당 포스트를 수정하실 권한이 없습니다.");
        }
        Room room = roomRepository.findById(requestDto.getRoom_id()).orElse(null);
        if (room == null) {
            return badRequest("해당하는 숙소정보가 없습니다.");
        }
        post.update(requestDto, room);
        return success("포스트 수정에 성공하셨습니다.",post);
    }

    public ResponseEntity<CommonResponse<?>> deletePost(Long postId,Long userId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return badRequest("해당하는 포스트가 없습니다.");
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return badRequest("해당 포스트를 삭제하실 권한이 없습니다.");
        }
        return success("포스트 삭제에 성공하였습니다.",post);
    }
    private ResponseEntity<CommonResponse<?>> success(String msg,Post post){
        return ResponseEntity.ok(CommonResponse.<PostResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg(msg)
                .data(new PostResponseDto(post))
                .build());
    }

    private ResponseEntity<CommonResponse<?>> badRequest(String msg) {
        return ResponseEntity.badRequest().body(CommonResponse.<PostResponseDto>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .msg(msg)
                .data(null)
                .build());
    }

}
