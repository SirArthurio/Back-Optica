package com.vaxi.spring_boot_optica.Controller;

import com.vaxi.spring_boot_optica.Service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/factura")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @PostMapping("/crear/{userId}")
    public ResponseEntity<Map<String, String>> crearFactura(@PathVariable String userId) {
        Map<String, String> response = new HashMap<>();
        ResponseEntity<?> resultado = facturaService.crearFactura(userId);
        response.put("mensaje", "Factura creada exitosamente" + resultado);
        return ResponseEntity.ok(response);
    }

}
