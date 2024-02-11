package com.sparta.roombnb;

import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.repository.PostRepository;
import com.sparta.roombnb.repository.UserRepository;
import jakarta.persistence.Column;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RoombnbApplicationTests {

    @Test
    void contextLoads() {
    }


}
