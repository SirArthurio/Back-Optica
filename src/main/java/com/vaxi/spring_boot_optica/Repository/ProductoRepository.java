package com.vaxi.spring_boot_optica.Repository;

import com.vaxi.spring_boot_optica.Model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto, String> {

}
