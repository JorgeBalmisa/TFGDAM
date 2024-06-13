package com.example.main.controller;

import com.example.main.model.Prestamo;
import com.example.main.service.servicesImpl.PrestamoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/prestamos")
public class PrestamoControllerImpl extends BaseControllerImpl<Prestamo, PrestamoServiceImpl> {

    @PostMapping("/cliente/{clienteId}/save")
    public ResponseEntity<?> createPrestamo(
            @PathVariable Long clienteId,
            @RequestParam List<Long> libroIds,
            @RequestBody Prestamo prestamo) {
        Prestamo newPrestamo = servicio.createPrestamo(clienteId, libroIds, prestamo);
        return ResponseEntity.ok(newPrestamo);
    }

    @GetMapping("/searchBy/clienteNombre")
    public ResponseEntity<?> findByClienteNombre(@RequestParam String nombre) {
        List<Prestamo> prestamos = servicio.findPrestamosByClienteNombre(nombre);
        return ResponseEntity.status(HttpStatus.OK).body(prestamos);
    }

    @GetMapping("/searchBy/libroNombre")
    public ResponseEntity<?> findByLibroNombre(@RequestParam String nombre) {
        List<Prestamo> prestamos = servicio.findPrestamosByLibroNombre(nombre);
        return ResponseEntity.status(HttpStatus.OK).body(prestamos);
    }

    @PutMapping("/update/{prestamoId}/fecha-devolucion/")
    public ResponseEntity<?> updateFechaDevolucion(@PathVariable Long prestamoId, @RequestBody String nuevaFecha) {
        Prestamo prestamo = servicio.updatePrestamoFechaDevolucion(prestamoId, nuevaFecha);
        return ResponseEntity.status(HttpStatus.OK).body(prestamo);
    }

    @Override
    @DeleteMapping("/delete/{prestamoId}")
    public ResponseEntity<?> delete(@PathVariable Long prestamoId) {
        servicio.deletePrestamo(prestamoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
