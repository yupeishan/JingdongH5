package com.example.jingdong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    /**
     * 用于读取配置文件中 swagger 属性是否开启
     */
    @Value("${swagger.enable}")
    Boolean swaggerEnabled;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 是否开启swagger
                .enable(true)
                .select()
                // 过滤条件，扫描指定路径下的文件
                .apis(RequestHandlerSelectors.basePackage("com.example.jingdong.controller"))
                // 指定路径处理，PathSelectors.any()代表不过滤任何路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        /*作者信息*/
        Contact contact = new Contact("于沛汕", "https://www.gaoshiyi.top", "peishan.yu@foxmail.com");
        return new ApiInfo(
                "仿京东到家前后端分离程序接口文档",
                "仿京东到家前后端分离程序接口文档",
                "v1.0",
                "https://www.gaoshiyi.top",
                contact,
                "",
                "",
                new ArrayList<>()
        );
    }


}