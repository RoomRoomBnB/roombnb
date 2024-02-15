package com.sparta.roombnb.dto;

import com.sparta.roombnb.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

@Getter
@Setter
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

    public RoomDto(JSONObject jsonObject) throws JSONException {
        this.addr = jsonObject.getString("addr1");
        this.areacode = jsonObject.getString("areacode");
        this.title =jsonObject.getString("title");
        this.img = jsonObject.getString("firstimage");
        this.tel = jsonObject.getString("tel");
        this.contentId = jsonObject.getString("contentid");
        this.ratingAvg = 0L;
        this.postList = null;
    }
    public RoomDto(JSONObject jsonObject,List<Post> postList) throws JSONException {
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
