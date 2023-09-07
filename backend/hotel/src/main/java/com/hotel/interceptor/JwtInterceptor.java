package com.hotel.interceptor;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hotel.common.StandardCode;
import com.hotel.entity.system.User;
import com.hotel.exception.ServiceException;
import com.hotel.service.system.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT拦截器
 *
 * @author 小李哞哞
 * @date 2023/5/23
 */
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        String baseUrl = "/hotel/api";

        // 排除接口文档接口
        if (requestURI.startsWith(baseUrl + "/doc.html")
                || requestURI.startsWith(baseUrl + "/webjars/")
                || requestURI.startsWith(baseUrl + "/v2/api-docs")
                || requestURI.startsWith(baseUrl + "/swagger-resources"))
        {
            return true;
        }

        // 如果不是请求接口直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 审查是否有token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(StandardCode.CODE_401, "登录失效，请重新登录");
        }

        // 获取token中的id
        String id = null;
        try {
            id = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new ServiceException(StandardCode.CODE_401, "登录失效，请重新登录");
        }

        // 根据token中的id获取用户对象
        User user = userService.getById(id);

        if (ObjUtil.isEmpty(user)) {
            throw new ServiceException(StandardCode.CODE_401, "登录失效，请重新登录");
        }

        // 用户密码加签验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(StandardCode.CODE_401, "登录失效，请重新登录");
        }

        // 放行
        return true;
    }
}
