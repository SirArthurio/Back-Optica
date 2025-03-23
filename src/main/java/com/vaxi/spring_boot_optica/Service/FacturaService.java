package com.vaxi.spring_boot_optica.Service;

import com.vaxi.spring_boot_optica.Model.Carrito;
import com.vaxi.spring_boot_optica.Model.Factura;
import com.vaxi.spring_boot_optica.Model.Producto;
import com.vaxi.spring_boot_optica.Repository.CarritoRepository;
import com.vaxi.spring_boot_optica.Repository.FacturaRepository;
import com.vaxi.spring_boot_optica.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FacturaService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Transactional
    public ResponseEntity<Map<String, String>> crearFactura(String userId) {
        System.out.println(">>> Iniciando creación de factura para usuario: " + userId);

        List<Carrito> carrito = carritoRepository.findByUserId(userId);

        if (carrito.isEmpty()) {
            System.out.println(">>> El carrito está vacío");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", "El carrito está vacío"));
        }

        for (Carrito item : carrito) {
            Optional<Producto> productoOpt = productoRepository.findById(item.getProductoId().getId());
            if (productoOpt.isEmpty()) {
                System.out.println(">>> Producto no encontrado: " + item.getProductoId().getId());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("mensaje", "El producto con ID " + item.getProductoId().getId() + " no existe."));
            }

            Producto producto = productoOpt.get();
            if (producto.getStock() < item.getCantidad()) {
                System.out.println(">>> Stock insuficiente para producto: " + producto.getNombre());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("mensaje", "No hay suficiente stock para el producto: " + producto.getNombre()));
            }
        }

        System.out.println(">>> Actualizando stock de productos...");
        for (Carrito item : carrito) {
            Producto producto = productoRepository.findById(item.getProductoId().getId()).get();
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);
        }

        System.out.println(">>> Creando la factura...");
        Factura factura = new Factura();
        factura.setUserId(userId);
        factura.setTotal((int) carrito.stream().mapToDouble(item -> item.getCantidad() * item.getProductoId().getPrecio()).sum());
        facturaRepository.save(factura);

        System.out.println(">>> Factura creada con ID: " + factura.getId());

        System.out.println(">>> Eliminando carrito...");
        carritoRepository.deleteAll(carrito);

        System.out.println(">>> Proceso de compra finalizado correctamente.");

        return ResponseEntity.ok(Map.of("mensaje", "Factura creada exitosamente"));
    }

}
