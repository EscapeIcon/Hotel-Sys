package com.hotel.controller.system;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hotel.common.Result;
import com.hotel.common.StandardCode;
import com.hotel.dto.UserDTO;
import com.hotel.entity.system.User;
import com.hotel.service.system.IUserService;
import com.hotel.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户管理
 *
 * @author 小李哞哞
 * @date 2023/5/21
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    /**
     * 模糊分页查找所有用户
     * @return
     */
    @ApiOperation(value = "模糊分页查询", notes = "模糊分页查询所有用户")
    @GetMapping("/page")
    public Result list(
            @ApiParam(value = "当前页", required = true) Long pageNum,
            @ApiParam(value = "每页显示记录条数", required = true) Long pageSize,
            @ApiParam(value = "模糊搜索") User user
    ) {
        IPage<User> userList = userService.queryLikePageList(pageNum, pageSize, user);
        return Result.success(userList);
    }

    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "返回token、菜单、权限信息")
    @PostMapping("/login")
    public Result login(
            @ApiParam(value = "登录必须的参数", required = true) @RequestBody UserDTO userDTO
    ) {
        userDTO = userService.login(userDTO);
        return Result.success(userDTO);
    }

    /**
     * 新增或修改用户信息
     * @param user
     * @return
     */
    @ApiOperation(value = "新增或修改", notes = "参数中有id则修改，无则新增")
    @PostMapping
    public Result save(
            @ApiParam(value = "用户信息", required = true) @RequestBody User user
    ) {
        boolean isOk = userService.saveOrUpdatePlus(user);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "保存用户信息失败");
    }

    /**
     * 根据id删除用户信息，并且删除用户角色关联表中的记录
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个用户", notes = "根据id删除用户信息，并且删除用户角色关联表中的记录")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        boolean isOk = userService.removeByIdPlus(id);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "删除用户失败");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除", notes = "根据ids批量删除, ids是id的数组，并且删除用户角色关联表的记录")
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(
            @ApiParam(value = "ids", required = true) @RequestBody List<Integer> ids
    ) {
        boolean isOk = userService.removeBatchByIdsPlus(ids);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "批量删除失败");
    }

    /**
     * 获取当前登录的用户
     * @return
     */
    @ApiOperation(value = "获取当前用户", notes = "根据token获取当前系统登录的用户信息")
    @GetMapping("/getCurUser")
    public Result getCurUser() {
        User user = TokenUtil.getCurrentUser();
        return Result.success(user);
    }

    /**
     * 上传头像
     * @param avatar
     * @param user
     * @return
     */
    @ApiOperation(value = "上传头像", notes = "上传头像至阿里云OSS")
    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(
            @ApiParam(value = "头像文件", required = true) MultipartFile avatar,
            @ApiParam(value = "用户信息", required = true) User user) {
        user = userService.uploadAvatar(avatar, user);
        if (ObjUtil.isEmpty(user)) {
            return Result.error(StandardCode.CODE_600, "更新数据库失败");
        }
        return Result.success(user.getAvatarUrl());
    }
}
