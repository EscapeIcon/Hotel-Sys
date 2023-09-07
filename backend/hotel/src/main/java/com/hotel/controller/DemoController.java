package com.hotel.controller;

import com.hotel.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小李哞哞
 * @date 2023/6/12
 */
@RestController
@Api(tags = "测试后端连通性")
public class DemoController {

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "测试后端是否成功启动")
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @ApiOperation(value = "测试Redis是否连通")
    @GetMapping("/testRedis")
    public String testRedis() {
        if (!redisUtil.hasKey("test:01")) {
            redisUtil.set("test:01", "这是一个测试数据");
        }
        System.out.println(redisUtil.get("test:01"));
        return "success";
    }
}
