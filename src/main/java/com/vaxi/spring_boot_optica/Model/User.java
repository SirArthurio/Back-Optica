package com.vaxi.spring_boot_optica.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User implements UserDetails {

  @JsonProperty("cedula")
  @NotNull(message = "La cedula no puede estar vacio")
  private String cedula;

  @JsonProperty("nombre")
  @NotEmpty(message = "El nombre no puede estar vacio")
  private String nombre;

  @JsonProperty("apellido")
  @NotEmpty(message = "El Apellido no puede estar vacio")
  private String apellido;

  @JsonProperty("rol")
  @NotEmpty(message = "El Rol no puede estar vacio")
  private Role rol;

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

  @JsonProperty("fecha_creacion")
  @NotNull(message = "La fecha no puede estar vacia")
  private LocalDateTime fecha_creacion;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return rol.getAuthorities();
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
