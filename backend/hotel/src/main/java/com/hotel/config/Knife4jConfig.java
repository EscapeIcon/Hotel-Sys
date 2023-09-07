package com.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class Knife4jConfig {

    @Bean
    public ApiInfo apiInfo() {
        Contact contact = new Contact("小李哞哞", "https://gitee.com/li-tianbao/hotel-sys", "1330622668@qq.com");
        return new ApiInfo(
                "酒店后台管理系统API文档",
                "酒店后台管理系统API文档",
                "v1.0",
                "https://gitee.com/li-tianbao/hotel-sys",
                contact,
                "许可证",
                "许可证URL",
                new ArrayList<>()
        );
    }

    @Bean
    public Docket docket(ApiInfo apiInfo){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
    }
}