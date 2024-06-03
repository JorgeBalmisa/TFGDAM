package com.example.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.entities.Persona;
import com.example.main.repositories.PersonaRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonaService implements BaseService<Persona> {

	@Autowired
	private PersonaRepository personaRepository;

	public PersonaService(PersonaRepository personaRepository) {
	this.personaRepository = personaRepository;
}

	@Override
	@Transactional
	public List<Persona> findAll() throws Exception {
		try {
			List<Persona> entities = personaRepository.findAll();
			return entities;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Persona findById(Long id) throws Exception {
		try {
			Optional<Persona> entityOptional = personaRepository.findById(id);
			return entityOptional.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Persona save(Persona entity) throws Exception {
		try {
			entity = personaRepository.save(entity);
			return entity;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Persona update(Long id, Persona entity) throws Exception {
		try {
			Optional<Persona> entityOptional = personaRepository.findById(id);
			if (entityOptional.isPresent()) {
				Persona persona = entityOptional.get();
				// Actualizar los campos de persona con los valores de entity
				persona.setNombre(entity.getNombre());
				persona.setApellido(entity.getApellido());
				// Actualiza otros campos según sea necesario
				return personaRepository.save(persona);
			} else {
				throw new Exception("Persona no encontrada");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public boolean delete(Long id) throws Exception {
		try {
			if (personaRepository.existsById(id)) {
				personaRepository.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
