package com.vaxi.spring_boot_optica.Service;

import com.vaxi.spring_boot_optica.Model.AuthResponse;
import com.vaxi.spring_boot_optica.Model.Carrito;
import com.vaxi.spring_boot_optica.Model.DTO.LoginDto;
import com.vaxi.spring_boot_optica.Model.DTO.RegisterDto;
import com.vaxi.spring_boot_optica.Model.DTO.UserDto;
import com.vaxi.spring_boot_optica.Model.User;
import com.vaxi.spring_boot_optica.Repository.CarritoRepository;
import com.vaxi.spring_boot_optica.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.vaxi.spring_boot_optica.Model.Role.PACIENTE;

@Service
public class CarritoService {


    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public CarritoService(CarritoRepository carritoRepository, ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
    }

    public Carrito addCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public List<Carrito> getAllCarrito() {

        return carritoRepository.findAll();
    }

    public Carrito editCarrito(String id) {

        return carritoRepository.findById(id).orElse(null);
    }

    public List<Carrito> getCarrito(String usuarioId) {

        return  carritoRepository.findByUserId(usuarioId);
    }
    public Carrito findByUserIdAndProductoId(String userId, String productoId) {
        return carritoRepository.findByUserIdAndProductoId(userId, productoId).orElse(null);
    }
    public Carrito findById(String id) {
        return carritoRepository.findById(id).orElse(null);
    }

    public void eliminarCarrito(String id) {
        carritoRepository.deleteById(id);
    }
    @Transactional
    public String actualizarCantidad(String carritoId, int nuevaCantidad) {
        Optional<Carrito> carritoOpt = carritoRepository.findById(carritoId);

        if (carritoOpt.isEmpty()) {
            return "No se encontró el producto en el carrito.";
        }

        Carrito carrito = carritoOpt.get();

        if (nuevaCantidad <= 0) {
            carritoRepository.delete(carrito);
            return "Producto eliminado del carrito.";
        }

        carrito.setCantidad(nuevaCantidad);
        carritoRepository.save(carrito);

        return "Cantidad actualizada correctamente.";
    }





}
