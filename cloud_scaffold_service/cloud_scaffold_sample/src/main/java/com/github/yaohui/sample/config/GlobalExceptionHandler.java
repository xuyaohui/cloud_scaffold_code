package com.github.yaohui.sample.config;

import com.github.yaohui.common.config.CommonException;
import com.github.yaohui.common.constants.CommonConstants;
import com.github.yaohui.common.rest.CommonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public CommonResponse commonExceptionHandler(CommonException e){
        return new CommonResponse(CommonConstants.ERROR_CODE, e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public CommonResponse runtimeExceptionHandler(RuntimeException e){
        return new CommonResponse(CommonConstants.ERROR_CODE, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse exceptionHandler(Exception e){
        return new CommonResponse(CommonConstants.ERROR_CODE, e.getMessage());
    }
}
