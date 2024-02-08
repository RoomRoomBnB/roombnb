package com.sparta.roombnb.service;

import com.sparta.roombnb.dto.BookmarkRequestDto;
import com.sparta.roombnb.dto.BookmarkResponseDto;
import com.sparta.roombnb.entity.Bookmark;
import com.sparta.roombnb.entity.User;
import com.sparta.roombnb.repository.BookmarkRepository;
import com.sparta.roombnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    public List<BookmarkResponseDto> createBookmark(BookmarkRequestDto request, User user) {
        Post post = findPost(request.getPostId());
        bookmarkRepository.save(new Bookmark(user, post));
        return findBookmarkByUser(user).stream().map(BookmarkResponseDto::new).toList();
    }

    public List<BookmarkResponseDto> getBookmark(User user) {
        return findBookmarkByUser(user).stream().map(BookmarkResponseDto::new).toList();
    }

    public void deleteBookmark(BookmarkRequestDto request) {
        Bookmark bookmark = findBookmarkByPostId(request.getPostId());
        bookmarkRepository.delete(bookmark);
    }


    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }
    private List<Bookmark> findBookmarkByUser(User user){
        return postRepository.findByUser(user).orElseThrow(() -> new RejectedExecutionException("해당 사용자가 존재하지 않습니다."));
    }
    private Bookmark findBookmarkByPostId(Long id){
        return postRepository.findByPost(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }
}
