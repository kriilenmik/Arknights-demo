package com.example.spring3.controller;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.spring3.common.Constants;
import com.example.spring3.common.Result;
import com.example.spring3.entity.ManagerInformation;
import com.example.spring3.entity.ManagerUser;
import com.example.spring3.service.impl.ManagerInfoServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/manager/information")
public class ManagerInfoController {

    @Autowired
    ManagerInfoServerImpl managerInfoServerImpl;

    @GetMapping("/page")
    public IPage<ManagerInformation> findByPage(@RequestParam Integer pageNum,
                                                @RequestParam Integer pageSize,
                                                @RequestParam(value = "textType", required = false) String textType

    )
    {
        return managerInfoServerImpl.find_By_Page1(pageNum, pageSize, textType);

    }

    @GetMapping("/pageFind")
    public List<ManagerInformation> pageFind(@RequestParam(value = "textType") String textType)
    {
        return managerInfoServerImpl.pageFind(textType);
    }

    // 新增或修改
    @PostMapping
    public boolean save(@RequestBody ManagerInformation managerInformation){
        return managerInfoServerImpl.saveInfo(managerInformation);
    }

    // 根据ID删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id)
    {
        return managerInfoServerImpl.removeById(id);
    }

    // 批量根据ID删除
    @DeleteMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return managerInfoServerImpl.removeByIds(ids);
    }



}
