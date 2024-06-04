package com.example.main.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.entities.Persona;
import com.example.main.repositories.BaseRepository;
import com.example.main.repositories.PersonaRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService{

	@Autowired
	private PersonaRepository personaRepository;

	public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository) {
		super(baseRepository);
}

	
}
