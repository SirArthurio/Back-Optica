package com.vaxi.spring_boot_optica.Repository;

import com.vaxi.spring_boot_optica.Model.Carrito;
import com.vaxi.spring_boot_optica.Model.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacturaRepository extends MongoRepository<Factura,String> {
}
