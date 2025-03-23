package com.vaxi.spring_boot_optica.Repository;

import com.vaxi.spring_boot_optica.Model.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoRepository extends MongoRepository<Carrito, String> {
    List<Carrito> findByUserId(String userId);
    Optional<Carrito> findByUserIdAndProductoId(String userId, String productoId);

}
