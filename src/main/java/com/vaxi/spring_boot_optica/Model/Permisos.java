package com.vaxi.spring_boot_optica.Model;

import lombok.Getter;

@Getter
public enum Permisos {
  PACIENTE_LEER("paciente:read"),
  PACIENTE_CREAR("paciente:create"),
  PACIENTE_ACTUALIZAR("paciente:update"),
  PACIENTE_ELIMINAR("paciente:delete"),

  OPTOMETRA_LEER("optometra:read"),
  OPTOMETRA_CREAR("optometra:create"),
  OPTOMETRA_ACTUALIZAR("optometra:update"),
  OPTOMETRA_ELIMINAR("optometra:delete"),

  DEV_LEER("dev:read"),
  DEV_CREAR("dev:create"),
  DEV_ACTUALIZAR("dev:update"),
  DEV_ELIMINAR("dev:delete"),

  ADMIN_LEER("admin:read"),
  ADMIN_CREAR("admin:create"),
  ADMIN_ACTUALIZAR("admin:update"),
  ADMIN_ELIMINAR("admin:delete");

  private final String permisos;

  Permisos(String permisos) {
    this.permisos = permisos;
  }
}
