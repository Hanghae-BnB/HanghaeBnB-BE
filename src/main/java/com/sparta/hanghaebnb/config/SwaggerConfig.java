package com.sparta.hanghaebnb.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "항해bnb 클론코딩",
                description = "기본적인 CRUD 게시판 API 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi OpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("항해bnb API v1")
                .pathsToMatch(paths)
                .build();
    }


}
