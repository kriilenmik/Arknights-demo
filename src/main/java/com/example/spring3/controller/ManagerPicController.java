package com.example.spring3.controller;


import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.spring3.common.Result;
import com.example.spring3.entity.ManagerInformation;
import com.example.spring3.entity.ManagerPic;
import com.example.spring3.service.impl.ManagerPicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/manager/pic")
public class ManagerPicController {

    @Autowired
    ManagerPicServiceImpl managerPicService;


    // ===================================== 走马灯图下载上传管理 ================================

    // 存储路径   F:\compiler\Idea2021\project\vue_spring_project\spring3
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";

    @PostMapping("/upload/pic")
    public Result upload(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();   // 文件原始名称

        String mainName = FileUtil.mainName(originalFilename);  // 文件名+扩展
        String extName =  FileUtil.extName(originalFilename);           //png

        if (!FileUtil.exist(ROOT_PATH)){
            FileUtil.mkdir(ROOT_PATH);          // 如果当前文件的父级目录不存在，则创建
        }

        if (FileUtil.exist(ROOT_PATH + File.separator + originalFilename)){                     // 如果当前上传文件已经存在，则重命名该文件名称
            originalFilename = System.currentTimeMillis() + "_" + mainName + "." + extName;
        }

        File saveFile = new File(ROOT_PATH + File.separator + originalFilename);     //文件名加随机数防止文件名重复
        file.transferTo(saveFile);  // 存储本地文件到本地磁盘
        String url = "http://localhost:9090/manager/pic/download/" + originalFilename;


        return Result.success(url);          // 返回链接
    }


    @GetMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) throws IOException {

        String filePath =  ROOT_PATH + File.separator + filename;

        if (!FileUtil.exist(filePath)){
            return;
        }
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

    }

    @GetMapping("/page")
    public IPage<ManagerPic> findByPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam(value = "image_url", required = false) String image_url

    )
    {
        return managerPicService.find_By_Page2(pageNum, pageSize, image_url);

    }

    @GetMapping("/findAll")
    public List<ManagerPic> findAll(){
        return managerPicService.list();
    }

    // 新增或修改
    @PostMapping()
    public boolean save(@RequestBody ManagerPic managerPic){
        return managerPicService.saveInfo1(managerPic);
    }

    // 根据ID删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id)
    {
        return managerPicService.removeById(id);
    }

    // 批量根据ID删除
    @DeleteMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return managerPicService.removeByIds(ids);
    }

}
