package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByContentId(String contentId);


}
