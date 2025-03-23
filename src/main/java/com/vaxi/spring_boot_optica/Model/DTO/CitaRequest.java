package com.vaxi.spring_boot_optica.Model.DTO;

import com.vaxi.spring_boot_optica.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CitaRequest {
    private User userId;
    private String fecha; // Formato: "yyyy-MM-dd"
    private String hora;  // Formato: "HH:mm"
    private String motivo;
}
