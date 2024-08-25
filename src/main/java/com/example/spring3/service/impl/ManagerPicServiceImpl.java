package com.example.spring3.service.impl;


import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring3.entity.ManagerInformation;
import com.example.spring3.entity.ManagerPic;
import com.example.spring3.mapper.ManagerPicMapper;
import com.example.spring3.service.ManagerPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ManagerPicServiceImpl extends ServiceImpl<ManagerPicMapper, ManagerPic> implements ManagerPicService {

    private static final Log LOG = Log.get();

    @Autowired
    ManagerPicServiceImpl managerPicService;

    @Override
    public IPage<ManagerPic> find_By_Page2(Integer pageNum, Integer pageSize, String image_url){
        IPage<ManagerPic> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ManagerPic> queryWrapper = new QueryWrapper<>();
        if (image_url != null && !image_url.isEmpty()){
            queryWrapper.and(w -> w.like("text_type",image_url));
        }

        return page(page, queryWrapper);
    }

    @Override
    public boolean saveInfo1(ManagerPic managerPic){
        return saveOrUpdate(managerPic);
    }


}
