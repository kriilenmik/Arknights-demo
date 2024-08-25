package com.example.spring3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spring3.entity.ManagerInformation;

import java.util.List;

public interface ManagerInfoService extends IService<ManagerInformation> {

    IPage<ManagerInformation> find_By_Page1(Integer pageNum, Integer pageSize, String textType);

    List<ManagerInformation> pageFind(String textType);

    boolean saveInfo(ManagerInformation managerInformation);
}
