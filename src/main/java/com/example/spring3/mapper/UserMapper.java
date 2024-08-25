package com.example.spring3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.spring3.entity.User;
import com.example.spring3.entity.UserDTO;
import org.apache.ibatis.annotations.*;
//import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//ALTER TABLE sys_user AUTO_INCREMENT = 5;重置自动增长值为5
//添加查询接口

@Mapper     //把类注入spring容器，让spring找到数据
@Repository
public interface UserMapper extends BaseMapper<User> {

}
