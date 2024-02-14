package com.sparta.roombnb.dto;

import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class RoomDto {
    private  String addr;
    private String areacode;
    private String title;
    private String img;
    private String tel;
    private String contentId;
    private Long ratingAvg;
    private List<String> postList;

    public RoomDto(JSONObject jsonObject){
        this.addr = jsonObject.getString("addr1");
        this.areacode = jsonObject.getString("areacode");
        this.title =jsonObject.getString("title");
        this.img = jsonObject.getString("firstimage");
        this.tel = jsonObject.getString("tel");
        this.contentId = jsonObject.getString("contentid"); //I소문자일수도 있음
        this.ratingAvg = 0L;
        this.postList = null;
    }
    public RoomDto(JSONObject jsonObject,List<Post> postList){
        this.addr = jsonObject.getString("addr1");
        this.areacode = jsonObject.getString("areacode");
        this.title =jsonObject.getString("title");
        this.img = jsonObject.getString("firstimage");
        this.tel = jsonObject.getString("tel");
        this.contentId = jsonObject.getString("contentid");
        this.ratingAvg = postList.stream().mapToLong(Post::getRating).sum()/ postList.size();
        this.postList = postList.stream().map(Post::getTitle).toList();
    }


}
