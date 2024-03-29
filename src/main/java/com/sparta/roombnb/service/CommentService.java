package com.sparta.roombnb.service;


import com.sparta.roombnb.dto.Comment.CommentRequestDto;
import com.sparta.roombnb.dto.Comment.CommentResponseDto;
import com.sparta.roombnb.entity.Comment;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.CommentRepository;
import com.sparta.roombnb.repository.PostRepository;
import jakarta.transaction.Transactional;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow();
        //postId로 post 가져오기

        Comment comment = new Comment(requestDto);
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto,
        User user) {
        Comment comment = getUserComment(commentId, user);

        comment.setContent(requestDto.getContent());
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = getUserComment(commentId, user);

        commentRepository.delete(comment);
    }

    private Comment getUserComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (!user.getId().equals(comment.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }
        return comment;
    }
}
