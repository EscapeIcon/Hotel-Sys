package com.hotel.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口统一返回包装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("统一返回包装对象")
public class Result {
    @ApiModelProperty("状态码")
    private String code;
    @ApiModelProperty("消息")
    private String msg;
    @ApiModelProperty("返回的数据")
    private Object data;

    public static Result success() {
        return new Result(StandardCode.CODE_200, "", null);
    }

    public static Result success(Object data) {
        return new Result(StandardCode.CODE_200, "", data);
    }

    public static Result success(String msg, Object data) {
        return new Result(StandardCode.CODE_200, msg, data);
    }

    public static Result error() {
        return new Result(StandardCode.CODE_500, "系统错误", null);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }
}
