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

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "factura")
public class Factura {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("userId")
    @NotNull(message = "El Id del usuario no puede estar vacio")
    private String  userId;

    @DBRef(lazy = false)
    @JsonProperty("productos")
    @NotNull(message = "El ID del producto no puede estar vacio")
    private List<Producto> productos;

    @JsonProperty("total")
    @NotNull(message = "el total no puede estar vacio")
    private float total;
}
