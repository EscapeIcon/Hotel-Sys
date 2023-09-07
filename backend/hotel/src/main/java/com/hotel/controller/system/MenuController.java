package com.hotel.controller.system;

import com.hotel.common.Result;
import com.hotel.common.StandardCode;
import com.hotel.entity.system.Menu;
import com.hotel.service.system.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理
 *
 * @author 小李哞哞
 * @date 2023/5/24
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation("查询所有")
    @GetMapping
    public Result queryList() {
        List<Menu> menus = menuService.queryList();
        return Result.success(menus);
    }

    /**
     * 新增或修改菜单信息
     * @param menu
     * @return
     */
    @ApiOperation(value = "新增或修改", notes = "参数中有id则修改，无则新增")
    @PostMapping
    public Result save(@ApiParam("有id就修改，无则新增") @RequestBody Menu menu) {
        boolean isOk = menuService.saveOrUpdate(menu);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "保存菜单信息失败");
    }

    /**
     * 根据id删除菜单信息，并且删除角色菜单关联表的数据
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个菜单", notes = "根据id删除菜单信息，并且删除角色菜单关联表中的记录")
    @DeleteMapping("/{id}")
    public Result deleteById(
            @ApiParam(value = "要删除的菜单的id", required = true) @PathVariable Integer id
    ) {
        boolean isOk = menuService.removeByIdPlus(id);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "删除菜单失败");
    }

    /**
     * 批量删除，并且删除角色菜单关联表的数据
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除", notes = "根据ids批量删除, ids是id的数组")
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(
            @ApiParam(value = "ids", required = true) @RequestBody List<Integer> ids
    ) {
        boolean isOk = menuService.removeBatchByIdsPlus(ids);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "批量删除失败");
    }
}
