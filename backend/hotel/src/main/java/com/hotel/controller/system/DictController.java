package com.hotel.controller.system;

import com.hotel.common.Result;
import com.hotel.common.StandardCode;
import com.hotel.entity.system.Dict;
import com.hotel.service.system.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典管理
 *
 * @author 小李哞哞
 * @date 2023/5/23
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private IDictService dictService;

    @ApiOperation("根据类型查询所有")
    @GetMapping
    public Result queryList(
            @ApiParam(value = "字典类型", required = true) String type
    ) {
        List<Dict> dictList = dictService.listByType(type);
        return Result.success(dictList);
    }

    /**
     * 模糊分页查询字典信息
     * @param pageNum
     * @param pageSize
     * @param dict
     * @return
     */
    @ApiOperation(value = "模糊分页查询", notes = "模糊分页查询所有字典信息")
    @GetMapping("/page")
    public Result list(
            @ApiParam(value = "当前页", required = true) Long pageNum,
            @ApiParam(value = "每页显示记录条数", required = true) Long pageSize,
            @ApiParam(value = "模糊搜索") Dict dict
    ) {
        List<Dict> dictList = dictService.queryLikePageList(pageNum, pageSize, dict);
        return Result.success(dictList);
    }

    /**
     * 新增或修改字典信息
     * @param dict
     * @return
     */
    @ApiOperation(value = "新增或修改", notes = "参数中有id则修改，无则新增")
    @PostMapping
    public Result save(
            @ApiParam(value = "要新增或修改的字典信息", required = true) @RequestBody Dict dict
    ) {
        boolean isOk = dictService.updateOrInsert(dict);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "保存失败");
    }

    /**
     * 根据id删除字典信息
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个字典", notes = "根据id删除字典信息")
    @DeleteMapping("/{id}")
    public Result deleteById(
            @ApiParam(value = "要删除的字典的id", required = true) @PathVariable Integer id
    ) {
        boolean isOk = dictService.rmById(id);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "删除失败");
    }

    /**
     * 批量删除字典信息
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除", notes = "根据ids批量删除, ids是id的数组")
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(
            @ApiParam(value = "ids", required = true) @RequestBody List<Integer> ids
    ) {
        boolean isOk = dictService.rmBatch(ids);
        if (isOk) {
            return Result.success();
        }
        return Result.error(StandardCode.CODE_600, "批量删除失败");
    }
}
