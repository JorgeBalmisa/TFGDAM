package com.example.main.services;

import java.util.List;

import com.example.main.entities.Persona;

public interface PersonaService extends BaseService<Persona, Long>{

	List<Persona> search(String filtro) throws Exception;
}
