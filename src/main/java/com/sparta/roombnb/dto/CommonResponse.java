package com.sparta.roombnb.dto;


import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommonResponse<T> {

    private Integer statusCode;
    private String msg;
    private T data;

    public CommonResponse(Integer statusCode, String msg){
        this.statusCode = statusCode;
        this.msg = msg;
    }

}

