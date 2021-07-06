package com.github.yaohui.common.config;

import lombok.Data;

@Data
public class CommonException extends RuntimeException{
    private Integer code;
    private String message;

    public CommonException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
