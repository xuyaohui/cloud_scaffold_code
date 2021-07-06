package com.github.yaohui.common.rest;

import com.github.yaohui.common.constants.CommonConstants;

public class BaseController {

    public <T>CommonResponse buildReturnSuccess(T data){
        return new CommonResponse(CommonConstants.SUCCESS_CODE,CommonConstants.SUCCESS_MESSAGE, data);
    }

    public CommonResponse buildError(String message){
        return new CommonResponse(CommonConstants.ERROR_CODE,message);
    }
}
