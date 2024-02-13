package com.sparta.roombnb.repository;

import com.sparta.roombnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByContentId(String contentId);
}
