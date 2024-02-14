package com.sparta.roombnb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.roombnb.dto.RoomDto;
import com.sparta.roombnb.entity.Post;
import com.sparta.roombnb.entity.Room;
import com.sparta.roombnb.repository.PostRepository;
import com.sparta.roombnb.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoomService {

    private final RestTemplate restTemplate;
    private final RoomRepository roomRepository;

    private final PostRepository postRepository;

    public  RoomService(RestTemplateBuilder builder, RoomRepository roomRepository, PostRepository postRepository){
        this.restTemplate = builder.build();
        this.roomRepository = roomRepository;
        this.postRepository = postRepository;
    }

    public List<RoomDto> getRoom(String pageNo) throws JSONException {
        URI uri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/searchStay1")
                .queryParam("numOfRows", 15)
                .queryParam("pageNo", pageNo)
                .queryParam("serviceKey", "y4I41SqhA6sAXfVEK1nlJhhVpNX%2Fp0VhpSDvrkDhkv3jT5MPa3CMhl%2BmyeHE2%2BLZB3Jldhx22L1fcKCfEqmppA%3D%3D")
                .queryParam("MobileOS","ETC")
                .queryParam("MobileApp","RoomBnB")
                .queryParam("_type","json")
                .encode()
                .build()
                .toUri();
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        return fromJSONtoRoom(responseEntity.getBody());
    }

    public String findRoom(String contentId) throws JSONException {
        URI uri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/detailCommon1")
                .queryParam("serviceKey", "jvnBDRsa7dr6pSY15zC%2FoqH%2FjVs0siBgYo5FCQ%2BR2aE0eUJ%2BuTgR0poEG0RFSg3jnltjiWyDzcdDrvyjrYRXjg%3D%3D")
                .queryParam("MobileOS","ETC")
                .queryParam("MobileApp","RoomBnB")
                .queryParam("_type","json")
                .queryParam("contentId",contentId)
                .encode()
                .build()
                .toUri();
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        String responseBody = responseEntity.getBody();
        System.out.println(responseEntity.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // JSON 문자열을 JsonNode로 파싱합니다.
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            // 여기서 필요한 JSON 데이터를 가져와서 사용합니다.
            JsonNode itemsNode = jsonNode.path("response").path("body").path("items").path("contentId");
            String contentIdValue = itemsNode.toString();
            return contentIdValue;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RoomDto searchRoom(String contentId) throws JSONException {
        URI uri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/detailCommon1")
                .queryParam("serviceKey", "y4I41SqhA6sAXfVEK1nlJhhVpNX%2Fp0VhpSDvrkDhkv3jT5MPa3CMhl%2BmyeHE2%2BLZB3Jldhx22L1fcKCfEqmppA%3D%3D")
                .queryParam("MobileOS","ETC")
                .queryParam("MobileApp","RoomBnB")
                .queryParam("_type","json")
                .queryParam("contentId",contentId)
                .queryParam("defaultYN","y")
                .queryParam("firstImageYN","Y")
                .queryParam("areacodeYN","Y")
                .queryParam("addrinfoYN","Y")
                .queryParam("overviewYN","Y")
                .encode()
                .build()
                .toUri();
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        return fromJSONtoRoom(responseEntity.getBody()).get(0);
    }

    public List<RoomDto> fromJSONtoRoom(String responseEntity) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseEntity);
        JSONObject jsonResponse = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items");
        JSONArray jsonItem  = jsonResponse.getJSONArray("item");
        List<RoomDto> roomDtos = new ArrayList<>();

        for (Object item : jsonItem) {
            JSONObject items = (JSONObject)item;
            List<Room> roomList = roomRepository.findAllByContentId(items.getString("contentId"));
            List<Long> postIdList = roomList.stream().map(Room::getPostId).toList();
            List<Post> postList = new ArrayList<>();
            for(Long id: postIdList){
                postList.add(postRepository.findById(id).get());
            }
            if(postList.isEmpty()){
                RoomDto roomDto = new RoomDto(items);
                roomDtos.add(roomDto);
            }
            else{
                RoomDto roomDto = new RoomDto(items,postList);
                roomDtos.add(roomDto);
            }
        }

        return roomDtos;
    }
}
