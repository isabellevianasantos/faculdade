package com.greglixandrao.desafioapispringrailway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de cadastro de produtos")
                        .description("API para cadastro de produtos de uma loja física, desenvolvida para facilitar a gestão de produtos.")
                        .version("v1.1")
                );
    }
}
