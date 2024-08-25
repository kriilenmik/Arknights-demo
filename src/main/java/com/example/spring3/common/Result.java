package com.example.spring3.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口统一返回包装类
 * common包类封装接口 == > controller中太多接口返回类，处理起来太杂乱
 *     前后端约定请求
 **/

@Data
@NoArgsConstructor      // 允许无参构造
@AllArgsConstructor     // 允许有参构造
public class Result {
    private String code;    // 告诉前端请求是否成功
    private String msg;     // 后端返回前端请求失败原因
    private Object data;    // 通用Object类型，什么数据都可以放  => 用于返回给前端，后台应该要携带的数据

    // 成功结果 => code === 200 ,携带data对象返回前端
    public static Result success(Object data){
        return new Result(Constants.CODE_200, "", data);
    }
    public static Result success(){
        return new Result(Constants.CODE_200, "",null );
    }



    // 错误结果
    public static Result error(String code, String msg){
        return new Result(code, msg, null);
    }

}
