package com.smart.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "basicAuth";

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
            // Apply security globally
            .addSecurityItem(
                new SecurityRequirement().addList(SECURITY_SCHEME_NAME)
            )

            // Define BASIC auth scheme
            .components(
                new Components().addSecuritySchemes(
                    SECURITY_SCHEME_NAME,
                    new SecurityScheme()
                        .name(SECURITY_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")
                )
            )

            // API metadata
            .info(
                new Info()
                    .title("Smart Auth API")
                    .description("Spring Boot API secured with HTTP Basic Authentication")
                    .version("1.0.0")
            );
    }
}
