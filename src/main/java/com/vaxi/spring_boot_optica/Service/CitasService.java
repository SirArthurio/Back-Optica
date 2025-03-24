package com.vaxi.spring_boot_optica.Service;

import com.vaxi.spring_boot_optica.Model.Citas;
import com.vaxi.spring_boot_optica.Model.DTO.CitaDTO;
import com.vaxi.spring_boot_optica.Model.DTO.CitaRequest;
import com.vaxi.spring_boot_optica.Model.User;
import com.vaxi.spring_boot_optica.Repository.CitasRepository;
import com.vaxi.spring_boot_optica.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitasService {

    @Autowired
    private CitasRepository citaRepository;
    @Autowired
    private UserRepository userRepository;

    public Object apartarCita(CitaRequest citaRequest) {
        try {
            // Extraer el ID del usuario
            String userId = citaRequest.getUserId().getId();

            // Verificar si el usuario existe
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return new CitaDTO("Usuario no encontrado. Por favor, verifique el ID.", null);
            }

            LocalDate fecha = LocalDate.parse(citaRequest.getFecha());
            LocalTime hora = LocalTime.parse(citaRequest.getHora());


            if (citaRepository.existsByFechaAndHora(fecha, hora)) {

                List<LocalTime> horariosDisponibles = obtenerHorariosDisponibles(fecha, hora);

                return new CitaDTO("Horario ocupado. Opciones disponibles:", horariosDisponibles);
            } else {
                // Crear y guardar la cita
                Citas cita = new Citas();
                cita.setUserId(user); // Asignar el usuario
                cita.setFecha(fecha);
                cita.setHora(hora);
                cita.setMotivo(citaRequest.getMotivo());
                citaRepository.save(cita);
                return new CitaDTO("Cita agendada correctamente.", null);
            }
        } catch (Exception e) {
            System.err.println("Error al agendar la cita: " + e.getMessage());
            return new CitaDTO("Error al procesar la solicitud.", null);
        }
    }

    public List<LocalTime> obtenerHorariosDisponibles(LocalDate fecha, LocalTime horaSolicitada) {
        LocalTime horaInicio = LocalTime.of(9, 0); // 9:00 AM
        LocalTime horaFin = LocalTime.of(18, 0);   // 6:00 PM

        List<Citas> citas = citaRepository.findByFecha(fecha);
        List<LocalTime> horariosOcupados = new ArrayList<>();
        for (Citas cita : citas) {
            horariosOcupados.add(cita.getHora());
        }

        List<LocalTime> horariosDisponibles = new ArrayList<>();
        LocalTime horaActual = horaInicio;

        while (horaActual.isBefore(horaFin)) {
            if (!horariosOcupados.contains(horaActual)) {
                horariosDisponibles.add(horaActual);
            }
            horaActual = horaActual.plusHours(1); // Siguiente hora
        }

        return horariosDisponibles;
    }
    public List<Citas> obtenerCitasPorUsuario(User userId) {
        return citaRepository.findByUserIdOrderByFechaAscHoraAsc(userId);
    }

}

