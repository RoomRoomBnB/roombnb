package com.sparta.roombnb.service;

import com.sparta.roombnb.dto.RoomDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
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

@Slf4j
@Service
public class RoomService {

    private final RestTemplate restTemplate;

    public  RoomService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    public List<RoomDto> getRoom(String pageNo){
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

    public String findRoom(String contentId){
        URI uri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/detailCommon1")
                .queryParam("serviceKey", "y4I41SqhA6sAXfVEK1nlJhhVpNX%2Fp0VhpSDvrkDhkv3jT5MPa3CMhl%2BmyeHE2%2BLZB3Jldhx22L1fcKCfEqmppA%3D%3D")
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
        JSONObject jsonObject = new JSONObject(responseEntity);
        return jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("contentId").toString();
    }

    public RoomDto searchRoom(String contentId){
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
        JSONObject jsonObject = new JSONObject(responseEntity);
        return fromJSONtoRoom(responseEntity.getBody()).get(0);
    }

    public List<RoomDto> fromJSONtoRoom(String responseEntity) {
        JSONObject jsonObject = new JSONObject(responseEntity);
        JSONObject jsonResponse = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items");
        JSONArray jsonItem  = jsonResponse.getJSONArray("item");
        List<RoomDto> roomDtos = new ArrayList<>();

        for (Object item : jsonItem) {
            RoomDto roomDto = new RoomDto((JSONObject) item);
            roomDtos.add(roomDto);
        }

        return roomDtos;
    }
}
