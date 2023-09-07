package com.hotel.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * 菜单
 *
 * @author 小李哞哞
 * @date 2023/5/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@TableName("sys_menu")
@ApiModel("菜单实体")
public class Menu {
    @ApiModelProperty("菜单Id(主键)")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单路由")
    private String path;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("菜单描述")
    private String description;

    @ApiModelProperty("父级菜单id")
    private Integer pid;

    @ApiModelProperty("子菜单列表")
    @TableField(exist = false)
    private List<Menu> children;

}
