package com.vaxi.spring_boot_optica.Controller;
import com.vaxi.spring_boot_optica.Model.Producto;
import com.vaxi.spring_boot_optica.Service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductosService productosService;


    @PostMapping("/addProducto")
    public ResponseEntity<?> addProducto(@RequestBody Producto producto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {

                bindingResult
                        .getAllErrors()
                        .forEach(error -> System.out.println(error.getDefaultMessage()));
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }

            Producto saveProducto = productosService.addProducto(producto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(saveProducto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllProductos")
    public ResponseEntity<?> getAllProductos() {
        try {

            if (!productosService.getAllProductos().isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(productosService.getAllProductos());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro los productos");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPruducto/{_id}")
    public ResponseEntity<?> getProducto(@PathVariable("_id") String _id) {
        try {
            Optional<Producto> producto = productosService.getProducto(_id);
            if (producto.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.OK).body(producto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario " + HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteProducto/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try {
            if (productosService.existsById(id)) {
                productosService.deleteProducto(id);
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .build();
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("no existe el producto");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/editProducto/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") String id,@RequestBody Producto producto) {
        try {
            Producto data = productosService.editProducto(id);
            if (data != null) {
                data.setNombre(producto.getNombre());
                data.setDescripcion(producto.getDescripcion());
                data.setStock(producto.getStock());
                productosService.addProducto(data);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(data);
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el producto " + HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
