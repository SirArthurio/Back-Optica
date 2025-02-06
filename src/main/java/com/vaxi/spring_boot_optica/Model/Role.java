package com.vaxi.spring_boot_optica.Model;

import static com.vaxi.spring_boot_optica.Model.Permisos.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum Role {
  PACIENTE(Set.of(PACIENTE_LEER)),
  OPTOMETRA(Set.of(PACIENTE_LEER, PACIENTE_CREAR, PACIENTE_ACTUALIZAR, PACIENTE_ELIMINAR)),
  DEV(
      Set.of(
          PACIENTE_LEER,
          PACIENTE_CREAR,
          PACIENTE_ACTUALIZAR,
          PACIENTE_ELIMINAR,
          OPTOMETRA_ELIMINAR,
          OPTOMETRA_ACTUALIZAR,
          OPTOMETRA_CREAR,
          OPTOMETRA_LEER,
          ADMIN_ACTUALIZAR,
          ADMIN_CREAR,
          ADMIN_ELIMINAR,
          ADMIN_LEER)),
  ADMIN(
      Set.of(
          PACIENTE_LEER,
          PACIENTE_CREAR,
          PACIENTE_ACTUALIZAR,
          PACIENTE_ELIMINAR,
          OPTOMETRA_ELIMINAR,
          OPTOMETRA_ACTUALIZAR,
          OPTOMETRA_CREAR,
          OPTOMETRA_LEER));

  private final Set<Permisos> permisos;

  Role(Set<Permisos> permisos) {
    this.permisos = permisos;
  }

  public List<SimpleGrantedAuthority> getAuthorities() {

    var authorities =
        permisos.stream()
            .map(permiso -> new SimpleGrantedAuthority(permiso.name()))
            .collect(Collectors.toList());

    authorities.add(new SimpleGrantedAuthority( "ROL:"+ this.name()));
    return authorities;
  }
}
