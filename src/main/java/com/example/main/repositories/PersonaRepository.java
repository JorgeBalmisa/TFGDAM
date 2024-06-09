package com.example.main.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.main.entities.Persona;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {

	List<Persona> findByNombreContaining(String nombre);

	List<Persona> findByApellidoContaining(String apellido);

	List<Persona> findByNombreIsNot(String nombre);

	List<Persona> findByNombreEndingWith(String sufijo);

	List<Persona> findByDni(String dni);

	List<Persona> findByDomicilio_Localidad_Denominacion(String denominacion);

	List<Persona> findByDomicilio_CalleAndDomicilio_Numero(String calle, int numero);

}
