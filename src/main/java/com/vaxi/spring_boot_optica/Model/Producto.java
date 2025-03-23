package com.vaxi.spring_boot_optica.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "productos")
public class Producto {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("nombre")
    @NotNull(message = "El nombre no puede estar vacio")
    private String nombre;

    @JsonProperty("descripcion")
    @NotNull(message = "La descripcion no puede estar vacio")
    private String descripcion;

    @JsonProperty("precio")
    @NotNull(message = "El precio no puede estar vacio")
    private float precio;

    @JsonProperty("stock")
    @NotNull(message = "El stock no puede estar vacio")
    private Integer stock;

    @JsonProperty("categoria")
    @NotNull(message = "La categoria no puede estar vacio")
    private String categoria;

}
