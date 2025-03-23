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


import java.time.LocalDate;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "citas")
public class Citas {

    @Id
    @JsonProperty("id")
    private String id;
    @NotNull

    @DBRef(lazy = false)
    @JsonProperty("userId")
    private User userId;

    @JsonProperty("fecha")
    @NotNull(message = "La fecha no puede estar vacio")
    private LocalDate fecha;

    @JsonProperty("hora")
    @NotNull(message = "La hora no puede estar vacio")
    private LocalTime hora;

    @JsonProperty("motivo")
    @NotNull(message = "La disponibilidad no puede estar vacia")
    private String motivo;
}
