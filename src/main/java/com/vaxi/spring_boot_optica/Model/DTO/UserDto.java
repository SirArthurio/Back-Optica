package com.vaxi.spring_boot_optica.Model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaxi.spring_boot_optica.Model.Role;
import com.vaxi.spring_boot_optica.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    @JsonProperty("cedula")
    private String cedula;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("apellido")
    private String apellido;

    public UserDto(User user) {
        this.nombre = user.getNombre();
        this.direccion = user.getDireccion();
        this.cedula = user.getCedula();
        this.apellido = user.getApellido();
    }
}
