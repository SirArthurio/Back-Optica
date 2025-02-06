package com.vaxi.spring_boot_optica.Service;

import com.vaxi.spring_boot_optica.Model.Examenes.ExamenQuerato;
import com.vaxi.spring_boot_optica.Repository.ExamenQueratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenQueratoService {

    private final ExamenQueratoRepository examenQueratoRepository;

    @Autowired
    public ExamenQueratoService(ExamenQueratoRepository examenQueratoRepository) {
        this.examenQueratoRepository = examenQueratoRepository;
    }

    public ExamenQuerato addExamen(ExamenQuerato examen) {
        return examenQueratoRepository.save(examen);
    }


    public List<ExamenQuerato> getAllExamen() {
        return examenQueratoRepository.findAll();
    }

    public ExamenQuerato getExamen(String _id) {
        return examenQueratoRepository.findById(_id).orElse(null);
    }

    public void deleteExamen(String _id) {
        examenQueratoRepository.deleteById(_id);
    }
}
