package com.github.yaohui.common.rest;

import lombok.Data;

@Data
public class CommonResponse <T>{

    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public CommonResponse(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
