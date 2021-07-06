package com.github.yaohui.admin.service;

import com.github.yaohui.admin.mapper.CloudUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudUserService {

    @Autowired
    private CloudUserMapper cloudUserMapper;

    public CloudUserMapper getMapper(){
        return cloudUserMapper;
    }

}
