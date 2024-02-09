package com.sparta.roombnb.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Getter
@Setter
@Builder
public class CommonResponse<T> {

    private Integer statusCode;
    private String msg;
    private T data;


    public CommonResponse(Integer statusCode, String msg){
        this.statusCode = statusCode;
        this.msg = msg;
    }


}