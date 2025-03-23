package com.vaxi.spring_boot_optica.Repository;


import com.vaxi.spring_boot_optica.Model.Examenes.ExamenQuerato;
import com.vaxi.spring_boot_optica.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamenQueratoRepository extends MongoRepository<ExamenQuerato, String> {
    @Query("{ 'cedula': ?0 }")
    ExamenQuerato findPacienteByCedula(String cedula);
}


