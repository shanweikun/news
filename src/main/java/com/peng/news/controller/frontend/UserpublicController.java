package com.peng.news.controller.frontend;

import com.peng.news.model.po.UserPO;
import com.peng.news.service.UserService;
import com.peng.news.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController$
 *
 * @author shan
 * @date 2023/3/9$
 */

@Api(tags = "用户注册")
@RestController
@RequestMapping("/user")
public class UserpublicController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     * @param userPO
     * @return
     */
    @ApiOperation("添加用户")
    @PostMapping("/register")
    public Result addUser(@RequestBody UserPO userPO){
        int userId = userService.addUser(userPO);
        userService.setRolesForUser(userPO.getId(), new Integer[]{
                9
        });
        return Result.success("添加成功！");
    }
}
