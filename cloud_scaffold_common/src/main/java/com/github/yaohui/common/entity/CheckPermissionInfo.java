package com.github.yaohui.common.entity;

import lombok.Data;

/**
 * @author Ths Sun
 * @create 2020/7/23.
 */
@Data
public class CheckPermissionInfo{
    // 请求权限资源
    private PermissionInfo permissionInfo;
    // 是否有权限
    private Boolean isAuth;
}
