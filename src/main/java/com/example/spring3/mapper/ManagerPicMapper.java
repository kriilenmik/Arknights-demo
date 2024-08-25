package com.example.spring3.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.spring3.entity.ManagerPic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ManagerPicMapper extends BaseMapper<ManagerPic> {
}
