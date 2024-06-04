package com.example.main.repositories;

import org.springframework.stereotype.Repository;

import com.example.main.entities.Persona;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {

}
