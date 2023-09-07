package com.hotel.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 用户角色关系
 *
 * @author 小李哞哞
 * @date 2023/5/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@TableName("sys_user_role")
@ApiModel("用户角色关联实体")
public class UserRole {
    @ApiModelProperty("用户Id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("角色Id")
    @TableField("role_id")
    private Integer roleId;
}
