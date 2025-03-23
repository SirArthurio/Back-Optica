package com.vaxi.spring_boot_optica.Controller;

import com.vaxi.spring_boot_optica.Model.Examenes.Examen;
import com.vaxi.spring_boot_optica.Model.Examenes.ExamenQuerato;
import com.vaxi.spring_boot_optica.Model.User;
import com.vaxi.spring_boot_optica.Repository.UserRepository;
import com.vaxi.spring_boot_optica.Service.ExamenQueratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examen/querato")
public class ExamenQueratoController {
    @Autowired
    private ExamenQueratoService examenService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<?> addExamen(@RequestBody ExamenQuerato examen) {
        try {
            if (examen.getOjoIzquierdo() == null && examen.getOjoDerecho() == null &&
                    examen.getCedula() == null && Float.isNaN(examen.getMilimetroDerecho()) &&
                    Float.isNaN(examen.getMilimetroIzquierdo())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los campos son obligatorios");
            }
            if (examen.getOjoIzquierdo() == null) {
                    return ResponseEntity.badRequest().body("El ojo izquierdo es obligatorio");
            }
            if (examen.getOjoDerecho() == null) {
                return ResponseEntity.badRequest().body("El ojo derecho es obligatorio");
            }
            if (examen.getCedula() == null) {
                return ResponseEntity.badRequest().body("La cedula es obligatoria");
            }
            if (Float.isNaN(examen.getMilimetroDerecho())) {
                return ResponseEntity.badRequest().body("El Milimetro Derecho es obligatorio");
            }
            if (Float.isNaN(examen.getMilimetroIzquierdo())) {
                return ResponseEntity.badRequest().body("El  Milimetro  izquierdo es obligatorio");
            }
            User paciente = userRepository.findPacienteByCedula(examen.getCedula());
            if (paciente != null) {
                examen.setMilimetroDerecho(examen.getOjoDerecho().CalcularRadioCurvatura());
                examen.setMilimetroIzquierdo(examen.getOjoIzquierdo().CalcularRadioCurvatura());
                return ResponseEntity.ok(examenService.addExamen(examen));
            } else {
                return ResponseEntity.badRequest().body("no se encontro el paciente");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit/{cedula}")
    public ResponseEntity<?> editExamen(@PathVariable String cedula, @RequestBody ExamenQuerato examen) {
        try {
            if (cedula ==null){
                return ResponseEntity.badRequest().body("La cedula es obligatorio");
            }
            ExamenQuerato data = examenService.getExamen(cedula);
            if (data != null) {
                data.setOjoDerecho(examen.getOjoDerecho());
                data.setOjoIzquierdo(examen.getOjoIzquierdo());
                data.setComentario(examen.getComentario());
                return ResponseEntity.ok(examenService.addExamen(data));
            } else {
                return ResponseEntity.badRequest().body("no se encontro el examen");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getExamenes() {
        try {
            return ResponseEntity.ok(examenService.getAllExamen());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{_id}")
    public ResponseEntity<?> getExamen(@PathVariable String _id) {
        try {
            if(_id == null) {
                return ResponseEntity.badRequest().body("El id es obligatorio");
            }
            Examen lista = examenService.getExamen(_id);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{_id}")
    public ResponseEntity<?> deleteExamen(@PathVariable String _id) {
        try {
            if(_id == null) {
                return ResponseEntity.badRequest().body("El id es obligatorio");
            }
            examenService.deleteExamen(_id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
