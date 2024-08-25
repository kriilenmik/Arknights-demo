package com.example.spring3.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.log.Log;
import com.example.spring3.common.Constants;
import com.example.spring3.entity.User;
import com.example.spring3.entity.UserDTO;
import com.example.spring3.entity.Validation;
import com.example.spring3.exception.ServiceException;
import com.example.spring3.mapper.IUserMapper;
import com.example.spring3.service.IUserService;
import com.example.spring3.service.IValidationService;
import com.example.spring3.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 *  服务实现类
 */

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    @Autowired
    JavaMailSender javaMailSender;

    // 从yml配置文件获取其中数据
    @Value("${spring.mail.username}")
    private String from;

    private static final Log LOG = Log.get();

    @Resource
    private IValidationService validationService;


    @Override
    public UserDTO login(UserDTO userDTO){
        User one = getUserInfo(userDTO, true);
        if(one != null)
        {
            BeanUtil.copyProperties(one, userDTO, true); // 复制数据库查询的数据对象userDto
            // 设置token
            String token = TokenUtils.generatorToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public UserDTO register (UserDTO userDTO){
        User one = getUserInfo(userDTO, false);
        if (one == null)
        {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_401, "用户名不可用");
        }
        return null;
    }

    private User getUserInfo(UserDTO userDTO, boolean isCheckPass){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        if(isCheckPass){
        queryWrapper.eq("password", userDTO.getPassword());}
        User one;
        try{
            one = getOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_401, "错误");
        }
        return one;
    }

    @Override
    public void sendEmailCode(String email){

        Date now = new Date();      // 当前时间now
        SimpleMailMessage message = new SimpleMailMessage();

        // 查询是否已经发送（同类型验证码)
        QueryWrapper<Validation> validationQueryWrapper = new QueryWrapper<>();
        validationQueryWrapper.eq("email", email);
        validationQueryWrapper.eq("type", 1);
        validationQueryWrapper.gt("time", now);       // gt --> 小于数据库存入时间 即 验证码未过期，查询数据库没过期的code
        Validation validation = validationService.getOne(validationQueryWrapper);
        if (validation != null){
            throw new ServiceException("-1", "当前您的验证码仍然有效，请不要重复发送");
        }


        message.setFrom(from);   // 发送人
        message.setTo(email);    // 接收邮箱方
        message.setSubject("【山里灵活】邮箱验证码");  // 邮件主题
        message.setSentDate(now);   // 当前发送时间

        String code = RandomUtil.randomNumbers(4);      // 随机4位验证码
        message.setText("您本次的邮箱验证码：" + code + "。验证码有效时间5分钟。此验证码只用于验证用户系统修改密码使用，请勿将此信息以任何形式告知他人！"); // 邮件内容
        javaMailSender.send(message);


        // 发送验证码后，存入数据库

        validationService.saveCode(email, code, 1, DateUtil.offsetMinute(now, 5)); // 存入用户邮箱，验证码，验证码类型，有效时间（由now向后推5分钟）


    }

    @Override
    public void updatePassword(String email, String password){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        updateWrapper.eq("email", email);

        User user = getOne(updateWrapper);


        if (user != null){
            user.setPassword(password);
            updateById(user);
        }
    }

    @Override
    public void deleteByUsername(String username){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq("username", username);

        User user = getOne(userQueryWrapper);

        if (user != null) {
            remove(userQueryWrapper);
        }else{throw new ServiceException("-1", "用户异常或参数错误");}
    }


}
