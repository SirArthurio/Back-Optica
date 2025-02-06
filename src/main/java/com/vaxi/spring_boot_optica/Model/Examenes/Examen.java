package com.vaxi.spring_boot_optica.Model.Examenes;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Examen {

  @JsonProperty("surcurzal")
  @NotNull(message = "La  surcurzal no puede estar vacia")
  private String surcurzal;

  @JsonProperty("comentario")
  @NotNull(message = "el Comentario no puede estar vacia")
  private String comentario;

  @JsonProperty("cedula")
  @NotNull(message = "La cedula del paciente no puede estar vacia")
  private String cedula;
}

