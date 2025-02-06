package com.vaxi.spring_boot_optica.Service;

import com.vaxi.spring_boot_optica.Model.Examenes.ExamenRefraccion;
import com.vaxi.spring_boot_optica.Repository.ExamenRefraccionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamenRefraccionService {
    private final ExamenRefraccionRepository examenRefraccionRepository;

    @Autowired
    public ExamenRefraccionService(ExamenRefraccionRepository examenRepository) {
        this.examenRefraccionRepository = examenRepository;
    }

    public ExamenRefraccion addExamen(ExamenRefraccion examen) {
        return examenRefraccionRepository.save(examen);
    }

    public List<ExamenRefraccion> getAllExamen() {
        return examenRefraccionRepository.findAll();
    }

    public ExamenRefraccion getExamen(String _id) {
        return examenRefraccionRepository.findById(_id).orElse(null);
    }

    public void deleteExamen(String _id) {
        examenRefraccionRepository.deleteById(_id);
    }
}
