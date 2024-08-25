package com.example.spring3.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@TableName(value = "manager_image")
public class ManagerPic {
    @ApiModelProperty("ID")
    @TableId(type = IdType.AUTO,value = "id")
    private Integer id;

    @TableField(value = "image_text")
    @ApiModelProperty("轮转活动概述")
    private String imageText;

    @TableField(value = "image_time")
    @ApiModelProperty("轮转活动时间")
    private String imageTime;

    @TableField(value = "image_url")
    @ApiModelProperty("图片地址")
    private String imageUrl;

    @TableField(value = "url_text")
    @ApiModelProperty("公告地址")
    private String urlText;


}
