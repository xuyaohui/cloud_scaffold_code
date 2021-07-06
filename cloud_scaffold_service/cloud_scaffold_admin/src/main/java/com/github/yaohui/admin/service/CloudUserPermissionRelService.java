package com.github.yaohui.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yaohui.admin.entity.CloudPermission;
import com.github.yaohui.admin.entity.CloudUserPermissionRel;
import com.github.yaohui.admin.mapper.CloudPermissionMapper;
import com.github.yaohui.admin.mapper.CloudUserPermissionRelMapper;

import com.github.yaohui.common.entity.PermissionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CloudUserPermissionRelService {

    @Autowired
    private CloudUserPermissionRelMapper relMapper;

    @Autowired
    private CloudPermissionMapper cloudPermissionMapper;

    public CloudUserPermissionRelMapper getRelMapper(){
        return relMapper;
    }

    public List<PermissionInfo> getAllPermissionsByUserId(String userId){

        LambdaQueryWrapper<CloudUserPermissionRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CloudUserPermissionRel::getUserId,userId);
        List<String> permissions = relMapper.selectList(queryWrapper).stream().map(CloudUserPermissionRel::getPermissionCode).collect(Collectors.toList());

        LambdaQueryWrapper<CloudPermission> queryPermissionWrapper = new LambdaQueryWrapper<>();
        queryPermissionWrapper.in(CloudPermission::getCode, permissions);
        return cloudPermissionMapper.selectList(queryPermissionWrapper).stream().map(item ->
            PermissionInfo.builder().code(item.getCode()).name(item.getName()).type(item.getType()).build()
        ).collect(Collectors.toList());
    }
}
