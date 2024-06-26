package com.example.main.controller;

import com.example.main.model.Autor;
import com.example.main.model.Libro;
import com.example.main.service.servicesImpl.LibroServiceImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.main.model.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/libros")
public class LibroController extends BaseControllerImpl<Libro, LibroServiceImpl> {
	
	
	
	@GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            List<LibroDTO> libros = servicio.findAllLibros();
            return ResponseEntity.status(HttpStatus.OK).body(libros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Inténtelo más tarde\"}");
        }
    }

    @GetMapping("/searchTitulo")
    public ResponseEntity<?> searchNombre(@RequestParam String filtro) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchByTitulo(filtro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/searchGenero")
    public ResponseEntity<?> searchGenero(@RequestParam String filtro) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchByGenero(filtro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
    @GetMapping("/searchAutores")
    public ResponseEntity<?> searchAutores(@RequestParam String filtro) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchByAutores(filtro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
    @GetMapping("/searchEditorial")
    public ResponseEntity<?> searchEditorial(@RequestParam String filtro) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchByEditorial(filtro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Libro libro) {
        Libro updatedLibro = servicio.updateLibro(id, libro);
        return ResponseEntity.ok(updatedLibro);
    }

    @PutMapping("/update/{libroId}/autores/{autorId}")
    public ResponseEntity<?> addAutorToLibro(@PathVariable Long libroId, @PathVariable Long autorId) {
        Libro updatedLibro = servicio.addAutorALibro(libroId, autorId);
        return ResponseEntity.ok(updatedLibro);
    }
}
