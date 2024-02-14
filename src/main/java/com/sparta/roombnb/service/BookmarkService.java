package com.sparta.roombnb.service;

import com.sparta.roombnb.dto.BookmarkRequestDto;
import com.sparta.roombnb.dto.BookmarkResponseDto;
import com.sparta.roombnb.entity.Bookmark;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.BookmarkRepository;
import com.sparta.roombnb.repository.PostRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PostRepository postRepository;


    @Transactional
    public List<BookmarkResponseDto> createBookmark(BookmarkRequestDto request, User user) {
        Post post = findPost(request.getPostId());
        bookmarkRepository.save(new Bookmark(user, post));
        return findBookmarkByUser(user).stream().map(BookmarkResponseDto::new).toList();
    }

    public List<BookmarkResponseDto> getBookmark(User user) {
        return findBookmarkByUser(user).stream().map(BookmarkResponseDto::new).toList();
    }

    @Transactional
    public void deleteBookmark(BookmarkRequestDto request, User user) {
        bookmarkRepository.deleteByUserIdAndPostId(user.getId(), request.getPostId());
    }


    private Post findPost(Long id) {
        return postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    private List<Bookmark> findBookmarkByUser(User user) {
        return bookmarkRepository.findByUser(user).stream().toList();
    }

    private Bookmark findBookmarkByPostId(Long postId) {
        Post post = findPost(postId);
        return bookmarkRepository.findByPost(post)
            .orElseThrow(() -> new IllegalArgumentException("해당 북마크가 존재하지 않습니다."));
    }
}
