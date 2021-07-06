package com.github.yaohui.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yaohui.common.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("cloud_user")
public class CloudUser extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userId;
    private String userName;
    private String password;
}
