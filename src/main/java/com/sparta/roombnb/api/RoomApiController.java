package com.sparta.roombnb.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomApiController {

    private final RoomApiService roomApiService;

    @GetMapping("/get-rooms")
    public List<ItemDto> getItems()  {
        try{
            return roomApiService.getItems();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;

        }
    }
}
