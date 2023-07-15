package com.gmail.apachdima.desirejob.bookservice.config;

import com.gmail.apachdima.desirejob.bookservice.common.OpenApi;
import com.gmail.apachdima.desirejob.commonservice.constant.CommonConstant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
            .info(info())
            .addSecurityItem(new SecurityRequirement()
                .addList(CommonConstant.SWAGGER_SECURITY_SCHEMA_NAME.getValue()))
            .components(new Components()
                .addSecuritySchemes(CommonConstant.SWAGGER_SECURITY_SCHEMA_NAME.getValue(), new SecurityScheme()
                    .name(CommonConstant.SWAGGER_SECURITY_SCHEMA_NAME.getValue())
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }

    private Info info() {
        Info info = new Info();
        info.setTitle(OpenApi.OPEN_API_INFO_TITLE.getValue());
        return info;
    }
}
