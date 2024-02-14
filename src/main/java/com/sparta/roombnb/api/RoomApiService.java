package com.sparta.roombnb.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j(topic = "Room API")
@Service
public class RoomApiService {

    private final RestTemplate restTemplate;

    public RoomApiService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<ItemDto> getItems() throws URISyntaxException, JsonProcessingException, JSONException {
        // 요청 URL 만들기
        String url = "https://apis.data.go.kr/B551011/KorService1/searchStay1?MobileOS=ETC&MobileApp=RoombnbApplication&serviceKey=jvnBDRsa7dr6pSY15zC%252FoqH%252FjVs0siBgYo5FCQ%252BR2aE0eUJ%252BuTgR0poEG0RFSg3jnltjiWyDzcdDrvyjrYRXjg%253D%253D";
        URI uri = new URI(url);
        log.info("uri = " + uri);

        String response = restTemplate.getForObject(uri, String.class);

        Map<String, Object> map = new ObjectMapper().readValue(response, Map.class);
        Map<String, Object> responseMap = (Map<String, Object>) map.get("response");
        Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");
        Map<String, Object> itemsMap = (Map<String, Object>) bodyMap.get("items");
        List<Map<String, Object>> itemMap = (List<Map<String, Object>>) itemsMap.get("item");

        return fromJSONtoItems(itemMap);
    }

    private List<ItemDto> fromJSONtoItems(List<Map<String, Object>> itemMap) throws JSONException {
            List<ItemDto> items = new ArrayList<>();

            for (Map<String, Object> item : itemMap) {
                ItemDto itemDto = new ItemDto();

                String itemName = (String) item.get("itemName");
                int itemPrice = (int) item.get("itemPrice");

                itemDto.setTitle(itemName);
                itemDto.setLprice(itemPrice);


                items.add(itemDto);
            }

        return items;
    }

}
