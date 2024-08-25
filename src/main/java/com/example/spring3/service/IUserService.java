package com.example.spring3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spring3.entity.User;
import com.example.spring3.entity.UserDTO;

public interface IUserService extends IService<User> {
    UserDTO login(UserDTO userDTO);

    UserDTO register(UserDTO userDTO);

    void sendEmailCode(String email);

    void updatePassword(String email, String password);

    void deleteByUsername(String username);
}
