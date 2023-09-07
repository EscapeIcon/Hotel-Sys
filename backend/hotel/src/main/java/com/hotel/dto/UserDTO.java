package com.hotel.dto;

import com.hotel.entity.system.Menu;
import com.hotel.entity.system.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author 小李哞哞
 * @date 2023/5/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel("用户数据传输对象")
public class UserDTO extends User {
    /**
     * token
     */
    @ApiModelProperty("token")
    private String token;

    /**
     * 菜单信息，包含一级菜单、二级菜单，二级菜单在一级菜单的children属性里
     */
    @ApiModelProperty("菜单信息")
    private List<Menu> menus;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String captcha;

    /**
     * 单次登录唯一uuid，作为从redis里取验证码的key
     */
    @ApiModelProperty("登录请求唯一标识")
    private String uuid;
}
