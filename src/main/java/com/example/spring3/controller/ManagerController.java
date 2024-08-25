package com.example.spring3.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.spring3.common.Constants;
import com.example.spring3.common.Result;
import com.example.spring3.entity.ManagerUser;
import com.example.spring3.entity.User;
import com.example.spring3.entity.UserDTO;
import com.example.spring3.service.UserService;
import com.example.spring3.service.impl.ManagerUserServiceImpl;
import com.example.spring3.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerUserServiceImpl managerUserService;

    @Autowired
    UserServiceImpl userServiceimpl;

    @Autowired
    UserService userService;


    // 登录
    @PostMapping("/login")
    public Result Login(@RequestBody ManagerUser managerUser) {
        String username = managerUser.getUsername();
        String password = managerUser.getPassword();

        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_401, "账号或密码空");
        }

        managerUserService.login(managerUser);

        return Result.success();
    }

    // 查询所有
//    @GetMapping()
//    public List<ManagerUser> findAll() {
//        return managerUserService.list();
//    }

    // 分页查询
    @GetMapping("/page")
    public IPage<ManagerUser> findByPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "email", required = false) String email,
                                  @RequestParam(value = "nickname", required = false) String nickname
                                 )
    {
        return managerUserService.find_By_Page(pageNum, pageSize, username, email, nickname);

    }

    // 新增或更新
    @PostMapping
    public boolean save(@RequestBody ManagerUser managerUser){

       return managerUserService.saveManagerUser(managerUser);

    }

    // 根据ID删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id)
    {
        return managerUserService.removeById(id);
    }

    // 批量删除
    @DeleteMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return managerUserService.removeByIds(ids);
    }


    // 导出xlsx
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        //从数据库查询所有数据
        List<ManagerUser> list = managerUserService.list();
        //通过huTool工具创建writer写出到磁盘
        //ExcelWriter writer = ExcelUtil.getWriter(fileUploadPath+"\表格.xlsx");    //填入磁盘路径写入，不填则直接new

        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //自定义标题别名
        writer.addHeaderAlias("id","ID");
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
//        writer.addHeaderAlias("address","地址");
//        writer.addHeaderAlias("create_time","创建时间");

        //一次性写出list内的对象到excel，默认样式，强制输出标题
        writer.write(list, true);

        //设置浏览器固定响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String filename = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+ filename +".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }
}
