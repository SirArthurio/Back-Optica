package com.vaxi.spring_boot_optica.Model.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {
    @JsonProperty("cedula")
    @NotNull(message = "La cedula no puede estar vacio")
    private String cedula;

    @JsonProperty("nombre")
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;

    @JsonProperty("apellido")
    @NotEmpty(message = "El Apellido no puede estar vacio")
    private String apellido;

    @JsonProperty("direccion")
    @NotEmpty(message = "La direccion no puede estar vacia")
    private String direccion;

    @JsonProperty("telefono")
    @NotNull(message = "El telefono no puede estar vacio")
    private long telefono;

    @JsonProperty("username")
    @NotNull(message = "El usuario no puede estar vacio")
    private String username;

    @JsonProperty("password")
    @NotNull(message = "La contraseña no puedee estar vacia")
    private String password;

}
