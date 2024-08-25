package com.example.spring3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spring3.entity.ManagerUser;

public interface ManagerUserService extends IService<ManagerUser> {
    void login(ManagerUser managerUser);

//    ManagerUser  saveManagerUser(ManagerUser managerUser);
    boolean saveManagerUser(ManagerUser managerUser);

    IPage<ManagerUser> find_By_Page(Integer pageNum, Integer pageSize, String username, String email, String nickname);
}
