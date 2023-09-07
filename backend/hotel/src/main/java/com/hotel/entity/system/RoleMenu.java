package com.hotel.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 角色菜单关系
 *
 * @author 小李哞哞
 * @date 2023/5/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@TableName("sys_role_menu")
@ApiModel("角色菜单关联实体")
public class RoleMenu {
    @ApiModelProperty("角色Id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("菜单Id")
    @TableField("menu_id")
    private Integer menuId;
}
