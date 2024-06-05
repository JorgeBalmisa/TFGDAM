package com.example.main.servicesImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.entities.Persona;
import com.example.main.repositories.BaseRepository;
import com.example.main.repositories.PersonaRepository;
import com.example.main.services.PersonaService;

import jakarta.transaction.Transactional;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService{

	@Autowired
	private PersonaRepository personaRepository;

	public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository) {
		super(baseRepository);
}
	
	@Override
	public List<Persona> search(String filtro) throws Exception {
		try {
			List<Persona> personas = personaRepository.findByNombreContainingOrApellidoContaining(filtro, filtro);
			return personas;
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
}
