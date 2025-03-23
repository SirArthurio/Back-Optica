package com.vaxi.spring_boot_optica.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "carrito")
public class Carrito {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("userId")
    @NotNull(message = "El Id del usuario no puede estar vacio")
    private String  userId;

    @DBRef(lazy = false)
    @JsonProperty("productoId")
    @NotNull(message = "El ID del producto no puede estar vacio")
    private Producto productoId;

    @JsonProperty("cantidad")
    @NotNull(message = "la cantidad no puede estar vacio")
    private Integer cantidad;
}
