package com.example.spring3.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@TableName(value = "managerinformation")
public class ManagerInformation {
    @ApiModelProperty("ID")
    @TableId(type = IdType.AUTO,value = "id")
    private Integer id;

    @TableField(value = "text_type")
    @ApiModelProperty("公告类型")
    private String textType;

    @TableField(value = "text_time")
    @ApiModelProperty("公告时间")
    private String textTime;

    @TableField(value = "text_about")
    @ApiModelProperty("公告内容")
    private String textAbout;

    @TableField(value = "text_url")
    @ApiModelProperty("公告地址")
    private String textUrl;



}
