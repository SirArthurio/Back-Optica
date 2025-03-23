package com.vaxi.spring_boot_optica.Controller;

import com.vaxi.spring_boot_optica.Model.Citas;
import com.vaxi.spring_boot_optica.Model.DTO.CitaRequest;
import com.vaxi.spring_boot_optica.Service.CitasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
public class CitasController {
    private final CitasService citasService;

    public CitasController(CitasService citasService) {
        this.citasService = citasService;
    }


    @PostMapping("/apartar")
    public Object apartarCita(@RequestBody CitaRequest citaRequest) {
        return citasService.apartarCita(citaRequest);
    }

}
