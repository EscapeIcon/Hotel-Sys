package com.hotel.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 用户
 *
 * @author 小李哞哞
 * @date 2023/5/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@TableName("sys_user")
@ApiModel("用户实体")
public class User {
    @ApiModelProperty("用户id(主键)")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("用户状态")
    private Integer status;

    @ApiModelProperty("头像url")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private String createTime;

    @ApiModelProperty("用户角色")
    @TableField(exist = false)
    private Role role;
}
