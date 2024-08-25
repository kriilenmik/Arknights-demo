package com.example.spring3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spring3.entity.ManagerPic;

public interface ManagerPicService extends IService<ManagerPic> {

    IPage<ManagerPic> find_By_Page2(Integer pageNum, Integer pageSize, String image_url);

    boolean saveInfo1(ManagerPic managerPic);

}
