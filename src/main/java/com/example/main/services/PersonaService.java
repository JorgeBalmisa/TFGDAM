package com.example.main.services;

import java.util.List;

import com.example.main.entities.Persona;

public interface PersonaService extends BaseService<Persona, Long> {

	List<Persona> searchNombre(String filtro) throws Exception;

	List<Persona> searchApellido(String filtro) throws Exception;

	List<Persona> searchNotName(String filtro) throws Exception;

	List<Persona> searchNombreEnding(String filtro) throws Exception;

	List<Persona> searchDni(String filtro) throws Exception;
	
	List<Persona> searchDomicilio_Loc_Den(String filtro) throws Exception;
	
	List<Persona> searchDomicilio_Calle_Numero(String calle, Integer numero) throws Exception;

}
