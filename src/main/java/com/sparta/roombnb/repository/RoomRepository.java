package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByContentId(String contentId);


}
