package com.example.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.main.entities.Base;
import com.example.main.servicesImpl.BaseServiceImpl;

public abstract class BaseControllerImpl<T extends Base, S extends BaseServiceImpl<T, Long>> implements BaseController<T, Long>{

	@Autowired
	protected S servicio;
	
	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Inténtelo más tarde\"}");
		}
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		try {
			T entity = servicio.findById(id);
			if (entity != null) {
				return ResponseEntity.status(HttpStatus.OK).body(entity);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Persona no encontrada\"}");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Inténtelo más tarde\"}");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody T entity) {
		
		try {
			T savedEntity = servicio.save(entity);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Inténtelo más tarde\"}");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody T entity) {
		try {
			T updatedEntity = servicio.update(id, entity);
			return ResponseEntity.status(HttpStatus.OK).body(updatedEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al actualizar la persona\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			boolean deleted = servicio.delete(id);
			if (deleted) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Persona no encontrada\"}");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error al eliminar la persona\"}");
		}
	}
}
