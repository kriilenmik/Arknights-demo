package com.example.spring3.entity;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "validation")
public class Validation {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String email;

    private String code;

    private Integer type;

    private Date time;
}
