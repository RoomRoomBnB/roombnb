package com.sparta.roombnb.dto;

import com.sparta.roombnb.entity.Room;
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
    private String tel;
    private String contentId;

    public RoomDto(JSONObject jsonObject){
        this.addr = jsonObject.getString("addr1");
        this.areacode = jsonObject.getString("areacode");
        this.createdAt = jsonObject.getString("createdtime");
        this.title =jsonObject.getString("title");
        this.img = jsonObject.getString("firstimage");
        this.tel = jsonObject.getString("tel");
        this.contentId = jsonObject.getString("contentId"); //I소문자일수도 있음
    }

}
