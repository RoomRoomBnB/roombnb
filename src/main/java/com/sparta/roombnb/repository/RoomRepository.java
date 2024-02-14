package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByContentId(String contentId);

}
