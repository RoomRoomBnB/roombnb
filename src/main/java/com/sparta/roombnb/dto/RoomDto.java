package com.sparta.roombnb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RoomDto {
    private  String addr;
    private String areacode;
    private String createdAt;
    private String title;
    private String img;

    public RoomDto(JSONObject jsonObject){
        this.addr = jsonObject.getString("addr1");
        this.areacode = jsonObject.getString("areacode");
        this.createdAt = jsonObject.getString("createdtime");
        this.title =jsonObject.getString("title");
        this.img = jsonObject.getString("firstimage");
    }
}
