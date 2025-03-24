package com.vaxi.spring_boot_optica.Controller;

import com.vaxi.spring_boot_optica.Model.Citas;
import com.vaxi.spring_boot_optica.Model.DTO.CitaRequest;
import com.vaxi.spring_boot_optica.Model.User;
import com.vaxi.spring_boot_optica.Repository.CitasRepository;
import com.vaxi.spring_boot_optica.Service.CitasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/citas")
public class CitasController {
    private final CitasService citasService;
    private final CitasRepository citasRepository;

    public CitasController(CitasService citasService, CitasRepository citasRepository) {
        this.citasService = citasService;
        this.citasRepository = citasRepository;
    }


    @PostMapping("/apartar")
    public Object apartarCita(@RequestBody CitaRequest citaRequest) {
        return citasService.apartarCita(citaRequest);
    }
    @DeleteMapping("/citas/{citaId}")
    public ResponseEntity<?> cancelarCita(
            @PathVariable String citaId,
            @RequestHeader("Authorization") String token) {

        try {
            // 1. Verificar token/autorización


            // 2. Verificar si la cita existe
            Optional<Citas> citaOptional = citasRepository.findById(citaId);
            if (citaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
            }

            Citas cita = citaOptional.get();



            // 4. Eliminar la cita
            citasRepository.delete(cita);

            // 5. Notificar al usuario (opcional)
            notificarCancelacion(cita);

            return ResponseEntity.ok().body(Map.of(
                    "mensaje", "Cita cancelada correctamente",
                    "citaId", citaId
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cancelar la cita: " + e.getMessage());
        }
    }
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> getCitasByUsuario(
            @PathVariable String userId,
            @RequestHeader("Authorization") String token) {

        try {
            // 1. Validar token y obtener usuario autenticado
            // (Implementa tu lógica de autenticación aquí)

            // 2. Crear objeto User para la consulta
            User user = new User();
            user.setId(userId);

            // 3. Obtener citas
            List<Citas> citas = citasService.obtenerCitasPorUsuario(user);

            // 4. Retornar respuesta estructurada
            return ResponseEntity.ok().body(
                    Map.of(
                            "status", "success",
                            "data", citas,
                            "count", citas.size(),
                            "timestamp", LocalDateTime.now()
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of(
                            "status", "error",
                            "message", "Error al obtener citas: " + e.getMessage()
                    )
            );
        }
    }



    // Métodos auxiliares



    private void notificarCancelacion(Citas cita) {
        // Implementar notificación por email/push
    }

}
