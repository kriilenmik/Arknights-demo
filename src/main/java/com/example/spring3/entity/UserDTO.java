package com.example.spring3.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *  接收前端用户信息请求参数的实体类
 */

@Data
@TableName(value = "user")
public class UserDTO {

    private String username;

    private String password;

    private String token;

    private String nickname;

    private String email;

    private String code;
}
