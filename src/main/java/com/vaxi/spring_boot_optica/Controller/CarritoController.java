package com.vaxi.spring_boot_optica.Controller;

import com.vaxi.spring_boot_optica.Model.Carrito;
import com.vaxi.spring_boot_optica.Repository.CarritoRepository;
import com.vaxi.spring_boot_optica.Service.CarritoService;
import com.vaxi.spring_boot_optica.Service.ProductosService;
import com.vaxi.spring_boot_optica.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    private final CarritoRepository carritoRepository;
    private final CarritoService carritoService;
    private final UserService userService;
    private final ProductosService productosService;

    public CarritoController(CarritoRepository carritoRepository, CarritoService carritoService, UserService userService, ProductosService productosService) {
        this.carritoRepository = carritoRepository;
        this.carritoService = carritoService;
        this.userService = userService;
        this.productosService = productosService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> obtenerCarrito(@PathVariable String userId) {
        try {
            List<Carrito> carritos = carritoService.getCarrito(userId);
            if (carritos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró carrito para el usuario " + userId);
            }
            return ResponseEntity.ok(carritos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminarCarrito/{id}")
    public ResponseEntity<?> eliminarCarrito(@PathVariable String id) {
        try {
            Carrito carritoExistente = carritoService.findById(id);
            if (carritoExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el carrito con ID: " + id);
            }
            carritoService.eliminarCarrito(id);
            return ResponseEntity.ok(Map.of("message", "Carrito eliminado correctamente."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }
    @PutMapping("/{carritoId}/cantidad/{nuevaCantidad}")
    public ResponseEntity<?> actualizarCantidad(@PathVariable String carritoId, @PathVariable int nuevaCantidad) {
        String resultado = carritoService.actualizarCantidad(carritoId, nuevaCantidad);

        if (resultado.contains("No se encontró")) {
            return ResponseEntity.status(404).body(resultado);
        }

        return ResponseEntity.ok(Map.of("message","se actualizo"));
    }



    @PostMapping("/addCarrito")
    public ResponseEntity<?> agregarAlCarrito(@RequestBody Carrito carrito, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }
            if (carrito.getUserId() == null || carrito.getUserId().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID del usuario no puede estar vacío");
            }

            if (!userService.existUserById(carrito.getUserId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el usuario con ID: " + carrito.getUserId());
            }

            if (!productosService.existsById(carrito.getProductoId().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con ID: " + carrito.getProductoId());
            }

            Carrito carritoExistente = carritoService.findByUserIdAndProductoId(carrito.getUserId(), carrito.getProductoId().getId());

            if (carritoExistente != null) {
                carritoExistente.setCantidad(carritoExistente.getCantidad() + carrito.getCantidad());
                Carrito carritoActualizado = carritoService.addCarrito(carritoExistente);
                return ResponseEntity.status(HttpStatus.OK).body(carritoActualizado);
            }

            Carrito nuevoCarrito = carritoService.addCarrito(carrito);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }


}
