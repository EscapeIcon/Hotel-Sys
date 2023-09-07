package com.hotel.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hotel.common.Result;
import com.hotel.common.StandardCode;
import com.hotel.entity.system.Role;
import com.hotel.service.system.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author 小李哞哞
 * @date 2023/5/24
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 模糊分页查找所有角色
     * @return
     */
    @ApiOperation(value = "模糊分页查询", notes = "模糊分页查询所有角色")
    @GetMapping("/page")
    public Result list(
            @ApiParam(value = "当前页", required = true) Long pageNum,
            @ApiParam(value = "每页显示记录条数", required = true) Long pageSize,
            @ApiParam(value = "模糊搜索") Role role
    ) {
        IPage<Role> roleList = roleService.queryLikePageList(pageNum, pageSize, role);
        return Result.success(roleList);
    }

    /**
     * 新增或修改角色信息
     * @param role
     * @return
     */
    @ApiOperation(value = "新增或修改", notes = "参数中有id则修改，无则新增")
    @PostMapping
    public Result save(
            @ApiParam(value = "要新增或修改的角色信息", required = true) @RequestBody Role role
    ) {
        boolean isOk = roleService.saveOrUpdate(role);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "保存角色信息失败");
    }

    /**
     * 根据id删除角色信息，并且删除角色菜单关联表的记录
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个角色", notes = "根据id删除角色信息，并且删除角色菜单关联表中的记录")
    @DeleteMapping("/{id}")
    public Result deleteById(
            @ApiParam(value = "要删除的角色的id", required = true) @PathVariable Integer id
    ) {
        boolean isOk = roleService.removeByIdPlus(id);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "删除角色失败");
    }

    /**
     * 批量删除，并且删除角色菜单关联表的记录
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除", notes = "根据ids批量删除, ids是id的数组")
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(
            @ApiParam(value = "ids", required = true) @RequestBody List<Integer> ids
    ) {
        boolean isOk = roleService.removeBatchByIdsPlus(ids);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "批量删除失败");
    }

    /**
     * 赋予角色访问菜单的权限
     * @param roleId
     * @param menuIds
     * @return
     */
    @ApiOperation(value = "角色菜单分配", notes = "给指定角色分配指定菜单")
    @PostMapping("/grant/{roleId}")
    public Result grant(
            @ApiParam(value = "角色id", required = true) @PathVariable Integer roleId,
            @ApiParam(value = "菜单ids", required = true) @RequestBody List<Integer> menuIds
    ) {
        roleService.grant(roleId, menuIds);
        return Result.success();
    }

    /**
     * 查询角色所有的menuId
     * @param roleId
     * @return
     */
    @ApiOperation(value = "查询角色的菜单id", notes = "根据指定角色查看该角色所拥有的菜单id")
    @GetMapping("/{roleId}")
    public Result getMenuIdsByRoleId(
            @ApiParam(value = "角色id", required = true) @PathVariable Integer roleId
    ) {
        List<Integer> menuIds = roleService.getMenuIdsByRoleId(roleId);
        return Result.success(menuIds);
    }

    /**
     * 查询所有的role
     * @return
     */
    @ApiOperation(value = "查询所有角色", notes = "查询所有角色(不是模糊分页查询)")
    @GetMapping
    public Result queryList() {
        return Result.success(roleService.list());
    }
}
