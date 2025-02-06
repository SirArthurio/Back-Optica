package com.vaxi.spring_boot_optica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class Cors {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Aquí defines los orígenes permitidos (ajusta según tu necesidad)
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // Cambia esto por la URL de tu frontend

        // Métodos HTTP permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Encabezados permitidos
        configuration.setAllowedHeaders(List.of("*")); // Permite todos los encabezados

        // Permitir el uso de credenciales (cookies, tokens)
        configuration.setAllowCredentials(true);

        // Configurar CORS para todas las rutas de la API
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Esto aplica a todas las rutas

        return source;
    }
}
