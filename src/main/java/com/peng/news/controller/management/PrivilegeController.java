package com.peng.news.controller.management;

import com.peng.news.model.CustomizedPage;
import com.peng.news.model.Result;
import com.peng.news.model.po.RolePO;
import com.peng.news.model.vo.ResourceVO;
import com.peng.news.model.vo.RoleVO;
import com.peng.news.service.ResourceService;
import com.peng.news.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限管理相关接口
 * @author shan
 * @version 1.0
 * @date 2021/3/24 10:33
 */
@Api(tags = "权限管理相关接口")
@RestController
@RequestMapping("/management/role")
public class PrivilegeController {

    @Autowired
    RoleService roleService;

    @Autowired
    ResourceService resourceService;

    @GetMapping("/hello")
    public String hello(){
        return this.getClass().getName() + " hello";
    }

    /**
     * 分页查询角色列表
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询角色列表")
    @GetMapping("/")
    public Result<CustomizedPage<RoleVO>> roleList(@RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer pageSize){
        return Result.success(roleService.roleList(page, pageSize));
    }

    @ApiOperation("添加")
    @PostMapping("/")
    public Result addRole(@RequestBody RolePO rolePO){
        roleService.addRole(rolePO);
        return Result.success("添加成功！");
    }

    @ApiOperation("删除")
    @DeleteMapping("/{roleId}")
    public Result delRole(@PathVariable Integer roleId){
        roleService.delRole(roleId);
        return Result.success("删除成功！");
    }

    /**
     * 查询一个角色所有不是子菜单的资源的id列表
     * @param roleId
     * @return
     */
    @ApiOperation("查询一个角色所有不是子菜单的资源的id列表")
    @GetMapping("/{roleId}")
    public Result<List<Integer>> getAllNotSubMenuResourceOfUser(@PathVariable Integer roleId){
        return Result.success(roleService.getAllNotSubMenuResourceByRoleId(roleId));
    }

    /**
     * 给角色设置可访问的资源
     * @param roleId
     * @param resourceIds
     * @return
     */
    @ApiOperation("给角色设置可访问的资源")
    @PutMapping("/{roleId}")
    public Result setResourceForRole(@PathVariable Integer roleId,@RequestBody Integer[] resourceIds){
        roleService.setResourcesForRole(roleId, resourceIds);
        return Result.success("设置成功！");
    }


    /**
     * 查询所有一级菜单，并且包含子菜单
     * @return
     */
    @ApiOperation("查询所有一级菜单，并且包含子菜单")
    @GetMapping("/resources")
    public Result<List<ResourceVO>> getAllOneLevelMenuWithChildren(){
        return Result.success(resourceService.getOneLevelMenuWithChildren());
    }
}
