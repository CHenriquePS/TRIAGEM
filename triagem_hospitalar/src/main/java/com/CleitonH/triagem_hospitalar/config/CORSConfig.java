package com.CleitonH.triagem_hospitalar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica para todas as rotas
                .allowedOrigins("*") // Permite qualquer origem
                .allowedMethods("*") // Permite qualquer método HTTP
                .allowedHeaders("*"); // Permite qualquer cabeçalho
    }
}
