package com.hotel.controller.system;


import com.hotel.common.CacheConstants;
import com.hotel.common.Result;
import com.hotel.common.StandardCode;
import com.hotel.exception.ServiceException;
import com.hotel.utils.Base64;
import com.hotel.utils.CaptchaUtil;
import com.hotel.utils.IdUtil;
import com.hotel.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 公开接口类，不需要权限验证
 *
 * @author 小李哞哞
 * @date 2023/5/22
 */
@Api(tags = "公开接口类")
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CaptchaUtil captchaUtil;

    @ApiOperation("获取验证码图片")
    @GetMapping("/captchaImage")
    public Result getCaptcha() throws IOException {

        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String code = captchaUtil.getCaptchaText();
        BufferedImage captchaImage = captchaUtil.generateCaptchaImage();

        // 验证码有效期2分钟
        redisUtil.set(verifyKey, code, 2 * 60);

        FastByteArrayOutputStream os = new FastByteArrayOutputStream();

        try {
            ImageIO.write(captchaImage, "jpg", os);
        } catch (IOException e) {
            throw new ServiceException(StandardCode.CODE_600, e.getMessage());
        }

        Map<String, String> ret = new HashMap<>();
        ret.put("uuid", uuid);
        ret.put("img", Base64.encode(os.toByteArray()));
        ret.put("code", code);
        return Result.success(ret);
    }
}
