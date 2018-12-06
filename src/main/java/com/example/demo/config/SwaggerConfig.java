package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置类
 * http://localhost:8080/swagger-ui.html
 * Created by jcl on 2018/12/5
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 配置api文档
     */
    @Bean
    public Docket configApiDocs() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("错误请求").build());
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("未授权").build());
        responseMessageList.add(new ResponseMessageBuilder().code(403).message("禁止").build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("未找到").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(new ApiInfoBuilder()
                        .title("用户管理系统API文档")
                        .description("本文档描述xxxxxx")
                        .contact(new Contact("Magic Long", null, "147263180@qq.com"))
                        .version("v1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build();
    }




}
