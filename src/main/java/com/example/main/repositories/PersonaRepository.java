package com.example.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.main.entities.Persona;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {

List<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido);
	
	//boolean existsByDni(int dni);
	
	@Query(value = """
			SELECT p 
			FROM Persona p
			WHERE p.nombre
			LIKE %:filtro%
			OR p.apellido LIKE %:filtro%
			""")
	List<Persona>search(@Param("filtro")String filtro);
}
