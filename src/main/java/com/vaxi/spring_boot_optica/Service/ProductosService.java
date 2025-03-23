package com.vaxi.spring_boot_optica.Service;

import com.vaxi.spring_boot_optica.Model.Producto;
import com.vaxi.spring_boot_optica.Model.User;
import com.vaxi.spring_boot_optica.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductosService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto addProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    public Producto editProducto(String _id) {

        return productoRepository.findById(_id).orElse(null);
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProducto(String _id) {
        return productoRepository.findById(_id);
    }
    public boolean existsById(String _id) {
        return  productoRepository.existsById(_id);

    }

    public void deleteProducto(String _id) {
        productoRepository.deleteById(_id);
    }
}
