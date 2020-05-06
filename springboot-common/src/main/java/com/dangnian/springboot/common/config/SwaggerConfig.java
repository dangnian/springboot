package com.dangnian.springboot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chun.yin
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger2.enabled: true}")
    private boolean swaggerEnabled;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dangnian.springboot.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("《SwaggerDemo的演示案例--》")
                .description("description:项目摘要")
                .version("1.0")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact(new Contact("chun.yin", "", "2364352132@qq.com"))
                .build();
    }

}
