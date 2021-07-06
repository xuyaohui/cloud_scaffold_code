package com.github.yaohui.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yaohui.common.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("cloud_user_permission_rel")
public class CloudUserPermissionRel extends BaseEntity{

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userId;
    private String permissionCode;
}
