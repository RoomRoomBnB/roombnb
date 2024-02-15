package com.sparta.roombnb.service;

import com.sparta.roombnb.dto.RoomDto;
import com.sparta.roombnb.repository.PostRepository;
import com.sparta.roombnb.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final PostRepository postRepository;
    private final String serviceKey = "fcC99RtPwP2BUSASPlZ7ARus2QsBRo80GqVcbP%2BS2buisRMO7q9T8Zo8nkund3De%2B1YN0VYWelHzh9CHpBeSRQ%3D%3D";
    private final RestTemplate restTemplate;


    public RoomService(RestTemplate restTemplate, RoomRepository roomRepository, PostRepository postRepository) {
        this.restTemplate = restTemplate;
        this.roomRepository = roomRepository;
        this.postRepository = postRepository;
    }

    public List<RoomDto> getRoom(String pageNo) {
        String serviceKey = "fcC99RtPwP2BUSASPlZ7ARus2QsBRo80GqVcbP%2BS2buisRMO7q9T8Zo8nkund3De%2B1YN0VYWelHzh9CHpBeSRQ%3D%3D";
        int numOfRows = 5;
        String mobileOS = "ETC";
        String mobileApp = "AppTest";
        String type = "json";
        String listYN = "Y";
        String arrange = "A";


        String apiUrl = String.format("https://apis.data.go.kr/B551011/KorService1/searchStay1?serviceKey=%s&numOfRows=%d&pageNo=%s&MobileOS=%s&MobileApp=%s&_type=%s&listYN=%s&arrange=%s",
                serviceKey, numOfRows, pageNo, mobileOS, mobileApp, type, listYN, arrange);

        URI uri = URI.create(apiUrl);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        System.out.println("return " + (responseEntity.getBody()));
        System.out.println("uri " + uri);

        return getRoomDto(responseEntity.getBody());
    }

    public String findRoom(String contentId) {
        String serviceKey = "fcC99RtPwP2BUSASPlZ7ARus2QsBRo80GqVcbP%2BS2buisRMO7q9T8Zo8nkund3De%2B1YN0VYWelHzh9CHpBeSRQ%3D%3D";
        int numOfRows = 10;
        String mobileOS = "ETC";
        String mobileApp = "AppTest";
        String type = "json";
        String listYN = "Y";
        String arrange = "A";


        String apiUrl = String.format("https://apis.data.go.kr/B551011/KorService1/searchStay1?serviceKey=%s&numOfRows=%d&MobileOS=%s&MobileApp=%s&_type=%s&listYN=%s&arrange=%s",
                serviceKey, numOfRows, mobileOS, mobileApp, type, listYN, arrange);

        URI uri = URI.create(apiUrl);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return findContentId(responseEntity.getBody());
    }

    public RoomDto searchRoom(String contentId) {
        String serviceKey = "fcC99RtPwP2BUSASPlZ7ARus2QsBRo80GqVcbP%2BS2buisRMO7q9T8Zo8nkund3De%2B1YN0VYWelHzh9CHpBeSRQ%3D%3D";
        int numOfRows = 1000;
        String mobileOS = "ETC";
        String mobileApp = "AppTest";
        String type = "json";
        String listYN = "Y";
        String arrange = "A";


        String apiUrl = String.format("https://apis.data.go.kr/B551011/KorService1/searchStay1?serviceKey=%s&numOfRows=%d&MobileOS=%s&MobileApp=%s&_type=%s&listYN=%s&arrange=%s",
                serviceKey, numOfRows, mobileOS, mobileApp, type, listYN, arrange);
        URI uri = URI.create(apiUrl);
        System.out.println(uri);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        System.out.println("그만 " + (responseEntity.getBody()));
        return getOneRoomDto(responseEntity.getBody(), contentId);
    }

    public List<RoomDto> getRoomDto(String responseEntity) {
        List<RoomDto> roomDtos = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(responseEntity);
        JSONObject jsonResponse = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items");
        JSONArray jsonItem = jsonResponse.optJSONArray("item");

        if (jsonItem != null) {
            for (int i = 0; i < jsonItem.length(); i++) {
                JSONObject item = jsonItem.getJSONObject(i);
                RoomDto roomDto = new RoomDto();
                System.out.println(item);
                roomDto.setTitle(item.getString("title"));
                roomDto.setAddr(item.getString("addr1"));
                roomDto.setContentId(item.getString("contentid"));
                roomDto.setTel(item.getString("tel"));
                roomDto.setRatingAvg(0L);
                roomDto.setImg(item.getString("firstimage"));
                roomDto.setAreacode(item.getString("areacode"));

                roomDtos.add(roomDto);
            }
        }

        return roomDtos;
    }

    public RoomDto getOneRoomDto(String responseEntity, String content) {
        RoomDto roomDto = null;

        JSONObject jsonObject = new JSONObject(responseEntity);
        JSONObject jsonResponse = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items");
        JSONArray jsonItem = jsonResponse.optJSONArray("item");

        if (jsonItem != null) {
//            RoomDto roomDto = null;
            for (int i = 0; i < jsonItem.length(); i++) {
                JSONObject item = jsonItem.getJSONObject(i);
                roomDto = new RoomDto();
                System.out.println(item);
                String contentId = item.getString("contentid");
                if (content.equals(contentId)) {
                    roomDto = new RoomDto();
                    System.out.println("찾은 아이템: " + item);
                    roomDto.setTitle(item.getString("title"));
                    roomDto.setAddr(item.getString("addr1"));
                    roomDto.setTel(item.getString("tel"));
                    roomDto.setRatingAvg(0L);
                    roomDto.setImg(item.getString("firstimage"));
                    roomDto.setContentId(item.getString("contentid"));
                    roomDto.setAreacode(item.getString("areacode"));
                    break; // 원하는 아이템을 찾았으므로 루프 중단
                }


            }
        }

        return roomDto;
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