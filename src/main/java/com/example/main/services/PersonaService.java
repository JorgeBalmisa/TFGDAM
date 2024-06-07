package com.example.main.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.main.entities.Persona;

public interface PersonaService extends BaseService<Persona, Long>{

	List<Persona> searchNombre(String filtro) throws Exception;
	
	List<Persona> searchApellido(String filtro) throws Exception;
	
	List<Persona> searchNotName(String filtro) throws Exception;
	
	List<Persona> searchNombreEnding(String filtro) throws Exception;
	
	
}
