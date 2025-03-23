package com.vaxi.spring_boot_optica.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CitaDTO {
    private String mensaje;
    private List<LocalTime> horariosDisponibles;


}
