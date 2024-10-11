package com.test.secondlesson.config;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("DOG API")
                        .description("Api of dog")
                        .version("1.0")
                        .termsOfService("https://www.google.com")
                        .contact(new io.swagger.v3.oas.models.info.Contact().name("A"))
                        .license(new io.swagger.v3.oas.models.info.License().name("MIT").url("https://www.google.com")));
    }
}
