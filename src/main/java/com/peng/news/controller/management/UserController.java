package com.peng.news.controller.management;

import com.peng.news.model.CustomizedPage;
import com.peng.news.model.Result;
import com.peng.news.model.paramBean.QueryUserBean;
import com.peng.news.model.vo.RoleVO;
import com.peng.news.model.vo.UserVO;
import com.peng.news.service.RoleService;
import com.peng.news.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理相关接口
 * @author shan
 * @version 1.0
 * @date 2021/3/24 10:32
 */
@Api(tags = "用户管理相关接口")
@RestController
@RequestMapping("/management/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;



    /**
     * 分页条件查询用户列表
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation("分页条件查询用户列表")
    @GetMapping("/")
    public Result<CustomizedPage<UserVO>> userList(Integer page, Integer pageSize, QueryUserBean queryUserBean){
        return Result.success(userService.userList(page, pageSize, queryUserBean));
    }

//    /**
//     * 添加用户
//     * @param userPO
//     * @return
//     */
//    @ApiOperation("添加用户")
//    @PostMapping("/register")
//    public Result addUser(@RequestBody UserPO userPO){
//        userService.addUser(userPO);
//        return Result.success("添加成功！");
//    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/{userId}")
    public Result delUser(@PathVariable Integer userId){
        userService.delUser(userId);
        return Result.success("删除成功！");
    }

    /**
     * 锁定/解锁用户
     * @param userId
     * @param locked 是否锁定
     * @return
     */
    @ApiOperation("锁定解锁用户")
    @PutMapping("/locked/{userId}")
    public Result setUserLocked(@PathVariable Integer userId,@RequestParam(required = true) boolean locked){
        userService.setUserLocked(userId, locked);
        return Result.success((locked ? "锁定" : "解锁") + "成功！");
    }

    /**
     * 给用户设置角色
     * @param userId
     * @param roleIds
     * @return
     */
    @ApiOperation("给用户设置角色")
    @PutMapping("/roles/{userId}")
    public Result setRolesForUser(@PathVariable Integer userId, @RequestBody Integer[] roleIds){
        userService.setRolesForUser(userId, roleIds);
        return Result.success("设置成功！");
    }

    /**
     * 查询用户的角色列表
     * @param userId
     * @return
     */
    @ApiOperation("查询用户的角色列表")
    @GetMapping("/roles/{userId}")
    public Result<List<Integer>> getRolesOfUser(@PathVariable Integer userId){
        return Result.success(userService.getRolesOfUser(userId));
    }

    @GetMapping("/roles")
    public Result<List<RoleVO>> getAllRoles(){
        return Result.success(roleService.getAllRoles());
    }
}
