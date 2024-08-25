package com.example.spring3.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spring3.entity.Validation;

import java.util.Date;


public interface IValidationService extends IService<Validation> {
    void saveCode(String email, String code, Integer type, DateTime expireDate);

    void verifyEmailCode(String email, String code, Date now);

}
