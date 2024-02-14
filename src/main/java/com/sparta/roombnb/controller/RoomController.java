package com.sparta.roombnb.controller;

import com.sparta.roombnb.dto.RoomDto;
import com.sparta.roombnb.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Room API", description = "숙소 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "숙소 조회", description = "숙소 조회")
    @GetMapping("/rooms/{page}")
    public List<RoomDto> searchRooms(@PathVariable String page) {
        return roomService.getRoom(page);
    }

}
