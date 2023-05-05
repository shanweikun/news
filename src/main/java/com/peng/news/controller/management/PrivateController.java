package com.peng.news.controller.management;

import com.peng.news.model.Result;
import com.peng.news.model.po.UserPO;
import com.peng.news.model.vo.ResourceVO;
import com.peng.news.model.vo.UserVO;
import com.peng.news.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录用户共有的接口
 * @author shan
 * @version 1.0
 * @date 2021/3/24 16:39
 */
@Api(tags = "登录用户共有的接口")
@RestController
@RequestMapping("/management/private")
public class PrivateController {

    @Autowired
    UserService userService;


    /**
     * 得到当前登录用户的菜单
     * @return
     */
    @ApiOperation("得到当前登录用户的菜单")
    @GetMapping("/menu")
    public Result<List<ResourceVO>> getMenusOfCurUser(){
        return Result.success(userService.getMenusOfCurUser2(null));
    }

    /**
     * 查询个人资料
     * @return
     */
    @ApiOperation("查询个人资料")
    @GetMapping("/")
    public Result<UserVO> getPersonalInfo(){
        return Result.success(userService.getUserInfoByUserId(null));
    }

    /**
     * 修改个人信息
     * @return
     */
    @ApiOperation("删除个人信息")
    @PutMapping("/")
    public Result updatePersonalInfo(@RequestBody UserPO userPO){
        userService.updatePersonalInfo(userPO);
        return Result.success("修改成功！");
    }

    /**
     * 修改密码
     * @return
     */
    @ApiOperation("修改密码")
    @PutMapping("/key")
    public Result updatePersonalPassWord(String newPassword){
        userService.updatePersonalPassword(newPassword);
        return Result.success("修改成功！");
    }



}
