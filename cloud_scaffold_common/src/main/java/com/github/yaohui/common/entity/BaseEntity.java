package com.github.yaohui.common.entity;

import lombok.Data;

@Data
public class BaseEntity {

    private Long id;
    private String createTime;
    private String updateTime;
    private int isDelete;
}
