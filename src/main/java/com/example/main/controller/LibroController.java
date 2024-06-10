package com.example.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.entities.Libro;
import com.example.main.servicesImpl.LibroServiceImpl;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/libros")
public class LibroController extends BaseControllerImpl<Libro, LibroServiceImpl> {
	

	@GetMapping
	public ResponseEntity<?> getAllLibros() {
		try {
			List<Libro> libros = servicio.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(libros);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\": \"Error al obtener los libros\"}");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getLibroById(@PathVariable Long id) {
		try {
			Libro libro = servicio.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(libro);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Libro no encontrado\"}");
		}
	}

	@PostMapping
	public ResponseEntity<?> createLibro(@RequestBody Libro libro) {
		try {
			Libro nuevoLibro = servicio.save(libro);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error al crear el libro\"}");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateLibro(@PathVariable Long id, @RequestBody Libro libro) {
		try {
			Libro libroActualizado = servicio.update(id, libro);
			return ResponseEntity.status(HttpStatus.OK).body(libroActualizado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error al actualizar el libro\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLibro(@PathVariable Long id) {
		try {
			boolean deleted = servicio.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(deleted);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Libro no encontrado\"}");
		}
	}
}
