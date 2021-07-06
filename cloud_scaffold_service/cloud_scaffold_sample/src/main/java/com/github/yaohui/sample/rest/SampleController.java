package com.github.yaohui.sample.rest;

import com.github.yaohui.common.rest.BaseController;
import com.github.yaohui.common.rest.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(("/rest/sample"))
public class SampleController extends BaseController {

    @RequestMapping("/test")
    public CommonResponse test(){
        return buildReturnSuccess("request sample service success");
    }
}
