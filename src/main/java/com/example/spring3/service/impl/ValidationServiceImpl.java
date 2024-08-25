package com.example.spring3.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring3.common.Constants;
import com.example.spring3.entity.Validation;
import com.example.spring3.exception.ServiceException;
import com.example.spring3.mapper.ValidationMapper;
import com.example.spring3.service.IValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ValidationServiceImpl extends ServiceImpl<ValidationMapper, Validation> implements IValidationService {

    @Resource
    private IValidationService validationService;

    @Transactional
    @Override
    public void saveCode(String email, String code, Integer type, DateTime expireDate){

        Validation validation = new Validation();
        validation.setEmail(email);
        validation.setCode(code);
        validation.setType(type);
        validation.setTime(expireDate);

        // 删除同类型验证
        UpdateWrapper<Validation> validationUpdateWrapper = new UpdateWrapper<>();
        validationUpdateWrapper.eq("email", email);
        validationUpdateWrapper.eq("type", 1);
        remove(validationUpdateWrapper);


        // 插入新的code
        save(validation);
    }

    @Override
    public void verifyEmailCode(String email, String code, Date now){

        QueryWrapper<Validation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.eq("code", code);
        queryWrapper.gt("time", now);
        Validation validation = validationService.getOne(queryWrapper);

        if (validation == null){
            throw new ServiceException(Constants.CODE_401, "验证码错误或已过期");
        }
    }
}
