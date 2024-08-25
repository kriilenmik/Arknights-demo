package com.example.spring3.exception;


import com.example.spring3.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice

/**
 *如果抛出的事ServiceException异常，则调用该方法
 * @Param se 业务异常
 *
 */


public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handles(ServiceException se){
        return Result.error(se.getCode(), se.getMessage());
    }

//    @ExceptionHandler(ServiceException.class)
//    @ResponseBody
//    public Result globalException(ServiceException e){
//        e.printStackTrace();
//        return Result.error("500", "系统错误");
//    }
}
