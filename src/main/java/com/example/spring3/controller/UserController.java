package com.example.spring3.controller;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.example.spring3.common.Constants;
import com.example.spring3.common.Result;
import com.example.spring3.entity.User;
import com.example.spring3.entity.UserDTO;
import com.example.spring3.entity.Validation;
import com.example.spring3.exception.ServiceException;
import com.example.spring3.service.IUserService;
import com.example.spring3.service.UserService;
import com.example.spring3.service.impl.UserServiceImpl;
import com.example.spring3.service.impl.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")   //指定接口地址
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    UserServiceImpl userServiceimpl;
    @Autowired
    ValidationServiceImpl validationService;


    // RequestBody => 将前端的JSON转换成后台JAVA对象
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if (StrUtil.isBlank(username) || StrUtil.isBlank(password))
        {
            return Result.error(Constants.CODE_401, "账号或密码空");
        }
        UserDTO dto = userServiceimpl.login(userDTO);

        return Result.success(dto);
    }

    @PostMapping("register")
    public Result register(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if (StrUtil.isBlank(username) || StrUtil.isBlank(password))
        {
            return Result.error(Constants.CODE_401, "账号或密码不能为空");
        }
        return Result.success(userServiceimpl.register(userDTO));
    }

    @GetMapping("/find")
    public Result findByName(@RequestParam(value = "username")String username){
//        User user = userService.findByName(username);
        return Result.success(userService.findByName(username));
    }


    @GetMapping("/email/{email}")
    public Result sendEmailCode(@PathVariable String email){
        if (StrUtil.isBlank(email)){
            throw new ServiceException(Constants.CODE_401, "参数错误");
        }
        userServiceimpl.sendEmailCode(email);
        return Result.success();
    }

    @PostMapping("/email/verify")
    public Result emailCode (@RequestBody Validation validation){
        String email = validation.getEmail();
        String code = validation.getCode();
        Date now = new Date();      // 当前时间now

        if (StrUtil.length(code) < 4 || StrUtil.isBlank(code)){
            return Result.error(Constants.CODE_401, "参数错误");
        }

        validationService.verifyEmailCode(email, code, now);
        return Result.success();
    }

    @PostMapping("/email/change")
    public Result passChange (@RequestBody UserDTO userDTO){
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();

        userServiceimpl.updatePassword(email, password);

        return Result.success();
    }

    @DeleteMapping("/userDelete/{username}")
    public Result DeleteByUsername(@PathVariable String username){

        userServiceimpl.deleteByUsername(username);

        return Result.success();
    }

}
