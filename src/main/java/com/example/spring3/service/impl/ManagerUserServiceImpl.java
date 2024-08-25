package com.example.spring3.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring3.common.Constants;
import com.example.spring3.entity.ManagerUser;
import com.example.spring3.entity.User;
import com.example.spring3.exception.ServiceException;
import com.example.spring3.mapper.ManagerUserMapper;
import com.example.spring3.service.ManagerUserService;
import org.springframework.stereotype.Service;

@Service
public class ManagerUserServiceImpl extends ServiceImpl<ManagerUserMapper, ManagerUser> implements ManagerUserService {

    private static final Log LOG = Log.get();


    @Override
    public void login(ManagerUser managerUser) {
        ManagerUser one = getUserInfo(managerUser);

        if (one == null){
            throw new ServiceException(Constants.CODE_401, "账号或密码错误");
        }

    }



    @Override
    public boolean saveManagerUser(ManagerUser managerUser){
        return saveOrUpdate(managerUser);
    }

    @Override
    public IPage<ManagerUser> find_By_Page(Integer pageNum, Integer pageSize, String username, String email, String nickname){
        IPage<ManagerUser> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ManagerUser> queryWrapper = new QueryWrapper<>();
        if (username != null && !username.isEmpty()){
            queryWrapper.and(w -> w.like("username",username));
        }
        if (email != null && !email.isEmpty()){
            queryWrapper.and(w -> w.like("email", email));
        }
        return page(page, queryWrapper);
    }

    private ManagerUser getUserInfo(ManagerUser managerUser){
        QueryWrapper<ManagerUser> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("username", managerUser.getUsername());
        queryWrapper.eq("password", managerUser.getPassword());

        ManagerUser one;
        try{
            one = getOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_401, "错误");
        }
        return one;
    }
}
