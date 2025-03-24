package com.vaxi.spring_boot_optica.Repository;

import com.vaxi.spring_boot_optica.Model.Citas;
import com.vaxi.spring_boot_optica.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitasRepository  extends MongoRepository<Citas,String> {
    boolean existsByFechaAndHora(LocalDate fecha, LocalTime hora);

    List<Citas> findByFecha(LocalDate fecha);
    List<Citas> findByUserId(User userId);
    List<Citas> findByUserIdOrderByFechaAscHoraAsc(User userId);
}
