package com.example.spring3.utils;


import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * Jwt消息构成
 * token由三部分组成的
 * 头部（header)
 * 载荷（payload)
 * 签证（signature)
 * @author https://blog.csdn.net/gjtao1130/article/details/111658060
 *
 * 代码仅示例方法
 * 在生产环境中，不应该直接将密码作为密钥传递给算法
 * 需要避免直接在代码中硬编码
 */

public class TokenUtils {
    public static String generatorToken(String userId, String sign){
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) //2小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥

    }
}