package com.vaxi.spring_boot_optica.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class Jackson {

  @Bean
  public ObjectMapper objectMapper() {
    // Configuración personalizada si es necesario
    ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Formato bonito en JSON
    return objectMapper;
  }
}
