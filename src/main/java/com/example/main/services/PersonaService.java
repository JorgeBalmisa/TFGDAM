package com.example.main.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.main.entities.Persona;

public interface PersonaService extends BaseService<Persona, Long>{

	List<Persona> search(String filtro) throws Exception;
	
	
}
