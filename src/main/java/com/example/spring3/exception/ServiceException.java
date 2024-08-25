package com.example.spring3.exception;

import lombok.Getter;

/**
 * 自定义异常代码
 * RuntimeException = >  java里提供的运行时异常
 */

@Getter
public class ServiceException extends RuntimeException{
    private String code;

    //构造器
    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
