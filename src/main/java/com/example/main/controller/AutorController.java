package com.example.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.main.model.Autor;
import com.example.main.service.servicesImpl.AutorServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/autores")
public class AutorController extends BaseControllerImpl<Autor, AutorServiceImpl> {

    @Override
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Autor autor) {
        Autor autorCrear = servicio.createAutor(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorCrear);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Autor nuevoAutor) {
        Autor updatedAutor = servicio.updateAutor(id, nuevoAutor);
        return ResponseEntity.ok(updatedAutor);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servicio.deleteAutor(id);
        return ResponseEntity.noContent().build();
    }

}