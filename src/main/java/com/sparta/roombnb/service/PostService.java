package com.sparta.roombnb.service;

import static com.sparta.roombnb.service.StatusCheck.badRequest;
import static com.sparta.roombnb.service.StatusCheck.forBidden;
import static com.sparta.roombnb.service.StatusCheck.success;

import com.sparta.roombnb.dto.CommonResponse;
import com.sparta.roombnb.dto.Post.PostRequestDto;
import com.sparta.roombnb.dto.Post.PostResponseDto;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.Room;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.PostRepository;
import com.sparta.roombnb.repository.RoomRepository;
import com.sparta.roombnb.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    //게시글 작성 기능 - 숙소 정보를 가져와서 대조함
    @Transactional
    public ResponseEntity<CommonResponse<?>> createPost(PostRequestDto requestDto, User user) {
        String contentId = roomService.findRoom(requestDto.getContentId());
        if (contentId == null) {
            return badRequest("해당하는 숙소정보가 없습니다.");
        }
        Post post = new Post(requestDto, user, roomService.searchRoom(contentId));
        postRepository.save(post);
        roomRepository.save(new Room(post.getId(), contentId));
        return success("게시글 작성에 성공하셨습니다.", new PostResponseDto(post));
    }

    //게시글 전체 조회 기능 - 작성일 기준
    public ResponseEntity<CommonResponse<?>> getAllPost() {
        Optional<List<Post>> postList = postRepository.findAllByOrderByCreatedAtDesc();
        if (postList.get().isEmpty()) {
            return badRequest("현재 작성된 포스트가 없습니다.");
        }
        return success("전체 게시글 조회에 성공하셨습니다.",
            postList.get().stream().map(PostResponseDto::new).toList());
    }

    //게시글 전체 조회 기능 - 별점 높은 순
    public ResponseEntity<CommonResponse<?>> getAllPostOrderRating() {
        Optional<List<Post>> postList = postRepository.findAllByOrderByRatingDesc();
        if (postList.get().isEmpty()) {
            return badRequest("현재 작성된 포스트가 없습니다.");
        }
        return success("별점순 게시글 조회에 성공하셨습니다.",
            postList.get().stream().map(PostResponseDto::new).toList());
    }


    //특정 게시글 조회 기능
    public ResponseEntity<CommonResponse<?>> getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        //post.ifPresent(post1 -> badRequest("해당하는 포스트가 없습니다."));
        if (post.isEmpty()) {
            return badRequest("해당하는 포스트가 없습니다.");
        }
        return success("게시글 조회에 성공하셨습니다.", new PostResponseDto(post.get()));
    }

    //게시글 수정 기능 - 먼저 입력한 포스트가 있는지 검사 후 포스트 수정 권한을 검사 마지막으로 입력한 숙소의 존재유무를 검사
    @Transactional
    public ResponseEntity<CommonResponse<?>> updatePost(Long postId, PostRequestDto requestDto,
        Long userId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            return badRequest("해당하는 포스트가 없습니다.");
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return forBidden("해당 포스트를 수정할 권한이 없습니다.");
        }
        String contentId = roomService.findRoom(requestDto.getContentId());
        if (contentId == null) {
            return badRequest("해당하는 숙소정보가 없습니다.");
        }
        post.get().update(requestDto, roomService.searchRoom(contentId));
        return success("포스트 수정에 성공하셨습니다.", new PostResponseDto(post.get()));
    }

    //게시글 삭제 기능 - 입력한 포스트가 있는 지 검사 후 포스트 삭제 권한을 검사
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
        return success("포스트 삭제에 성공하셨습니다.", "");
    }


}
