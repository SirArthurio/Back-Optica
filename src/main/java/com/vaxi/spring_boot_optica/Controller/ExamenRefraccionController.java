package com.vaxi.spring_boot_optica.Controller;

import com.vaxi.spring_boot_optica.Model.Examenes.Examen;
import com.vaxi.spring_boot_optica.Model.Examenes.ExamenRefraccion;
import com.vaxi.spring_boot_optica.Model.User;
import com.vaxi.spring_boot_optica.Repository.UserRepository;
import com.vaxi.spring_boot_optica.Service.ExamenRefraccionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examen/refraccion")
public class ExamenRefraccionController {

    @Autowired
    private ExamenRefraccionService examenService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<?> addExamen(@RequestBody ExamenRefraccion examen) {
     try {
         User paciente = userRepository.findPacienteByCedula(examen.getCedula());
         if (paciente != null) {
             return ResponseEntity.ok(examenService.addExamen(examen));
         } else {
             return ResponseEntity.badRequest().body("no se encontro el paciente");
         }
     } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
     }
    }

    @PutMapping("edit/{_id}")
    public ResponseEntity<?> editExamen(@PathVariable String _id, @RequestBody ExamenRefraccion examen) {
        try {
            ExamenRefraccion data = examenService.getExamen(_id);
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
            List<ExamenRefraccion> lista = examenService.getAllExamen();
            return ResponseEntity.ok(lista);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{_id}")
    public ResponseEntity<?> getExamen(@PathVariable String _id) {
        try {
            Examen lista = examenService.getExamen(_id);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/refraccion/delete/{_id}")
    public ResponseEntity<?> deleteExamen(@PathVariable String _id) {
     try{
         examenService.deleteExamen(_id);
         return ResponseEntity.ok().build();
     }catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
     }
    }
}
