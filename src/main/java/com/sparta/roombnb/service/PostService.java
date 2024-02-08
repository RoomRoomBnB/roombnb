package com.sparta.roombnb.service;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.PostRequestDto;
import com.sparta.roombnb.dto.PostResponseDto;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.Room;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.PostRepository;
import com.sparta.roombnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.sparta.roombnb.service.StatusCheck.*;


@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public ResponseEntity<CommonResponse<?>> createPost(PostRequestDto requestDto, User user) {
        Optional<Room> room = roomRepository.findById(requestDto.getRoom_id());
        if (room.isEmpty()) {
            return badRequest("해당하는 숙소정보가 없습니다.");
        }
        Post post = new Post(requestDto, user, room.get());
        postRepository.save(post);
        return success("게시글 작성에 성공하셨습니다.", new PostResponseDto(post));
    }


    public ResponseEntity<CommonResponse<?>> getAllPost() {
        Optional<List<PostResponseDto>> postList = postRepository.findAllByOrderByCreatedAtDesc();
        if (postList.isEmpty()) {
            return badRequest("현재 작성된 포스트가 없습니다.");
        }
        return success("전체 게시글 조회에 성공하셨습니다.", postList);
    }


    public ResponseEntity<CommonResponse<?>> getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        //post.ifPresent(post1 -> badRequest("해당하는 포스트가 없습니다."));
        if (post.isEmpty()) {
            return badRequest("해당하는 포스트가 없습니다.");
        }
        return success("게시글 조회에 성공하셨습니다.", new PostResponseDto(post.get()));
    }


    @Transactional
    public ResponseEntity<CommonResponse<?>> updatePost(Long postId, PostRequestDto requestDto, Long userId) {
        Optional<Post> post = postRepository.findById(postId); // Optional이 협업에서 자주쓰인다!
        if (post.isEmpty()) {
            return badRequest("해당하는 포스트가 없습니다.");
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return forBidden("해당 포스트를 수정할 권한이 없습니다.");
        }
        Optional<Room> room = roomRepository.findById(requestDto.getRoom_id());
        if (room.isEmpty()) {
            return badRequest("해당하는 숙소정보가 없습니다.");
        }
        post.update(requestDto, room.get());
        return success("포스트 수정에 성공하셨습니다.", new PostResponseDto(post.get()));
    }

    @Transactional
    public ResponseEntity<CommonResponse<?>> deletePost(Long postId, Long userId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            return badRequest("해당하는 포스트가 없습니다.");
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return forBidden("해당 포스트를 삭제할 권한이 없습니다.");
        }
        postRepository.delete(post.get());
        return success("포스트 삭제에 성공하셨습니다.","");
    }

}
