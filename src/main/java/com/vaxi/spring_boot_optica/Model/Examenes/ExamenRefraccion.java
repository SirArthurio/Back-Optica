package com.vaxi.spring_boot_optica.Model.Examenes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaxi.spring_boot_optica.Model.Ojo.OjoRefraccion;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ExamenRefraccion")
public class ExamenRefraccion extends Examen {
  @JsonProperty("ojoizquierdo")
  @NotNull(message = "El ojo izquierdo no puede estar vacio")
  private OjoRefraccion OjoIzquierdo;
  @JsonProperty("ojoderecho")
  @NotNull(message = "El ojo derecho no puede estar vacio")
  private OjoRefraccion OjoDerecho;
}
