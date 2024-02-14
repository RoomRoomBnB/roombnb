package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.RoomDto;
import com.sparta.roombnb.service.RoomService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms/{page}")
    public List<RoomDto> searchRooms(@PathVariable String page) throws JSONException {
        return roomService.getRoom(page);
    }

    @GetMapping("/room/{roomId}")
    public RoomDto getRoom(@PathVariable String roomId) throws JSONException {
        return roomService.searchRoom(roomId);
    }



}
