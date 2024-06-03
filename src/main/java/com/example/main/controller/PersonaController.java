package com.example.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.main.entities.Persona;
import com.example.main.services.PersonaService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/personas")
public class PersonaController {
	private PersonaService personaService;
	
	 private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

	@Autowired
	public PersonaController(PersonaService personaService) {
		this.personaService = personaService;
		logger.info("PersonaController initialized");
	}

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(personaService.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Inténtelo más tarde\"}");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		try {
			Persona persona = personaService.findById(id);
			if (persona != null) {
				return ResponseEntity.status(HttpStatus.OK).body(persona);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Persona no encontrada\"}");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Inténtelo más tarde\"}");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Persona entity) {
		logger.info("POST /api/v1/personas/save called with entity: {}", entity);
		try {
			Persona savedPersona = personaService.save(entity);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedPersona);
		} catch (Exception e) {
			logger.error("Error saving persona: ", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Inténtelo más tarde\"}");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Persona entity) {
		try {
			Persona updatedPersona = personaService.update(id, entity);
			return ResponseEntity.status(HttpStatus.OK).body(updatedPersona);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al actualizar la persona\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			boolean deleted = personaService.delete(id);
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
