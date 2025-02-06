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
    @JsonProperty("username")
    private String username;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("rol")
    private Role rol;

    public UserDto(User user) {
        this.nombre = user.getNombre();
        this.rol = user.getRol();
        this.username = user.getUsername();
    }
}
