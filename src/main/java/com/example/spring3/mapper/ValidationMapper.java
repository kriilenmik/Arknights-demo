package com.example.spring3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.spring3.entity.Validation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ValidationMapper extends BaseMapper<Validation> {

}
