package com.vaxi.spring_boot_optica.Model.Examenes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaxi.spring_boot_optica.Model.Ojo.OjoQuerato;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamenQuerato extends Examen {
    @JsonProperty("ojoizquierdo")
    @NotNull(message = "El ojo izquierdo no puede estar vacio")
    private OjoQuerato OjoIzquierdo;
    @JsonProperty("ojoderecho")
    @NotNull(message = "El ojo izquierdo no puede estar vacio")
    private OjoQuerato OjoDerecho;
    @JsonProperty("milimetroIzquierdo")
    @NotNull(message = "El milimetro no puede estar vacio")
    private float milimetroIzquierdo;
    @JsonProperty("milimetroDerecho")
    @NotNull(message = "El milimetro no puede estar vacio")
    private float milimetroDerecho;
}
