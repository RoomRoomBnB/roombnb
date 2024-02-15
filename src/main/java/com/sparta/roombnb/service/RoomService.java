package com.sparta.roombnb.service;

import com.sparta.roombnb.dto.RoomDto;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.Room;
import com.sparta.roombnb.repository.PostRepository;
import com.sparta.roombnb.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RoomService {

    private final RestTemplate restTemplate;
    private final RoomRepository roomRepository;

    private final PostRepository postRepository;

    String service = URLDecoder.decode("y4I41SqhA6sAXfVEK1nlJhhVpNX%2Fp0VhpSDvrkDhkv3jT5MPa3CMhl%2BmyeHE2%2BLZB3Jldhx22L1fcKCfEqmppA%3D%3D", StandardCharsets.UTF_8);

    public RoomService(RestTemplateBuilder builder, RoomRepository roomRepository,
                       PostRepository postRepository) {
        this.restTemplate = builder.build();
        this.roomRepository = roomRepository;
        this.postRepository = postRepository;
    }

    public List<RoomDto> getRoom(String pageNo) throws URISyntaxException {
        String uriString = "http://apis.data.go.kr/B551011/KorService1/searchStay1" +
                "?numOfRows=15" +
                "&pageNo=" + pageNo +
                "&serviceKey=" + service +
                "&MobileOS=ETC" +
                "&MobileApp=RoomBnB" +
                "&_type=json";
        URI uri = URI.create(uriString);
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        return getRoomDto(responseEntity.getBody());
    }

    public String findRoom(String contentId) {
        String uriString = "http://apis.data.go.kr/B551011/KorService1/detailCommon1" +
                "?serviceKey=" + service +
                "&MobileOS=ETC" +
                "&MobileApp=RoomBnB" +
                "&_type=json" +
                "&contentId=" + contentId;
        URI uri = URI.create(uriString);
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        return findContentId(responseEntity.getBody());
    }

    public RoomDto searchRoom(String contentId) {
        String uriString = "http://apis.data.go.kr/B551011/KorService1/detailCommon1" +
                "?serviceKey=" + service +
                "&MobileOS=ETC" +
                "&MobileApp=RoomBnB" +
                "&_type=json" +
                "&contentId=" + contentId +
                "&defaultYN=Y" +
                "&firstImageYN=Y" +
                "&areacodeYN=Y" +
                "&addrinfoYN=Y" +
                "&overviewYN=Y";
        URI uri = URI.create(uriString);
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        return getRoomDto(responseEntity.getBody()).get(0);
    }

    public List<RoomDto> getRoomDto(String responseEntity) {
        JSONObject jsonObject = new JSONObject(responseEntity);
        JSONObject jsonResponse = jsonObject.getJSONObject("response").getJSONObject("body")
                .getJSONObject("items");
        JSONArray jsonItem = jsonResponse.getJSONArray("item");
        List<RoomDto> roomDtos = new ArrayList<>();

        for (Object item : jsonItem) {
            JSONObject items = (JSONObject) item;
            String contentId = items.getString("contentid");
            List<Room> roomList = roomRepository.findAllByContentId(contentId);
            List<Long> postIdList = roomList.stream().map(Room::getPostId).toList();
            List<Post> postList = new ArrayList<>();
            for (Long id : postIdList) {
                postList.add(postRepository.findById(id).get());
            }
            if (postList.isEmpty()) {
                RoomDto roomDto = new RoomDto(items);
                roomDtos.add(roomDto);
            } else {
                RoomDto roomDto = new RoomDto(items, postList);
                roomDtos.add(roomDto);
            }
        }

        return roomDtos;
    }

    public String findContentId(String responseEntity) {
        JSONObject jsonObject = new JSONObject(responseEntity);
        System.out.println(jsonObject);

        JSONObject jsonResponse = jsonObject.getJSONObject("response").getJSONObject("body")
                .getJSONObject("items");
        JSONArray jsonItem = jsonResponse.getJSONArray("item");
        JSONObject jsonObject1 = (JSONObject) jsonItem.get(0);
        String contentId = jsonObject1.getString("contentid");
        return contentId;
    }
}