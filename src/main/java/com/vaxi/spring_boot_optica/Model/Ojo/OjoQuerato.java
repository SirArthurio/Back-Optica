package com.vaxi.spring_boot_optica.Model.Ojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OjoQuerato {
    @JsonProperty("horizontal")
    @NotNull(message = "El coso horizontal no puede estar vacio")
    private float Horizontal;
    @JsonProperty("vertical")
    @NotNull(message = "El coso vertical no puede estar vacio")
    private float Vertical;
    @JsonProperty("eje")
    @NotNull(message = "El eje no puede estar vacio")
    private float Eje;



    public float CalcularPromedio() {
        return (Horizontal + Vertical) / 2;
    }

    public float CalcularRadioCurvatura() {
        return (float) (0.3375*1000/CalcularPromedio());
    }

}


