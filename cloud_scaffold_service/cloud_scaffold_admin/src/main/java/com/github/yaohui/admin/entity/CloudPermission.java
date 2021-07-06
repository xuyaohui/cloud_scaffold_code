package com.github.yaohui.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yaohui.common.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("cloud_permission")
public class CloudPermission extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String code;
    private String name;
    private String type;
}
