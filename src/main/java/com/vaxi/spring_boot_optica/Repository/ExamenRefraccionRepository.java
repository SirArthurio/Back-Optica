package com.vaxi.spring_boot_optica.Repository;

import com.vaxi.spring_boot_optica.Model.Examenes.ExamenRefraccion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamenRefraccionRepository extends MongoRepository<ExamenRefraccion, String> {}
