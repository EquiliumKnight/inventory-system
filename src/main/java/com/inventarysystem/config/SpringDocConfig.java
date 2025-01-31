package com.inventarysystem.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springDocBean() {
        Info info = new Info()
                        .title("System Managment Iventary API")
                        .version("1.0")
                        .contact(null)
                        .description("This API exposes endpoints.")
                        .termsOfService(null)
                        .license(null);

        return new OpenAPI().info(info).servers(List.of());
    }

}
