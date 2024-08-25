package com.example.spring3.service.impl;


import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring3.entity.ManagerInformation;
import com.example.spring3.mapper.ManagerInfoMapper;
import com.example.spring3.service.ManagerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ManagerInfoServerImpl extends ServiceImpl<ManagerInfoMapper, ManagerInformation> implements ManagerInfoService {

    private static final Log LOG = Log.get();

    @Autowired
    ManagerInfoServerImpl managerInfoServer;

    @Override
    public IPage<ManagerInformation> find_By_Page1(Integer pageNum, Integer pageSize, String textType){
        IPage<ManagerInformation> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ManagerInformation> queryWrapper = new QueryWrapper<>();
        if (textType != null && !textType.isEmpty()){
            queryWrapper.and(w -> w.like("text_type",textType));
        }

        return page(page, queryWrapper);
    }

    @Override
    public List<ManagerInformation> pageFind(String textType){

        Page<ManagerInformation> page = new Page<>(1, 3);
        page.addOrder(OrderItem.desc("id"));

        List<ManagerInformation> records = lambdaQuery()
                .eq(ManagerInformation::getTextType, textType)
                .page(page)
                .getRecords();

        return records;
    }

    @Override
    public boolean saveInfo(ManagerInformation managerInformation){
        return saveOrUpdate(managerInformation);
    }


}
