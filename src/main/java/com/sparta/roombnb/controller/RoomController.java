package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.RoomDto;
import com.sparta.roombnb.service.RoomService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms/{page}")
    public List<RoomDto> searchRooms(@PathVariable String page){
        return roomService.getRoom(page);
    }

}
