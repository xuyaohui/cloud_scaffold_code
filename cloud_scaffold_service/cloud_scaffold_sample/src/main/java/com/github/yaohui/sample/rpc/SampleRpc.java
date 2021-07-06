package com.github.yaohui.sample.rpc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/rpc/sample"))
public class SampleRpc {

    @RequestMapping("/test")
    public String test(){
        return "request sample service success";
    }
}
