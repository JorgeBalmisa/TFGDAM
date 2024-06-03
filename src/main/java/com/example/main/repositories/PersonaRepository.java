package com.example.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main.entities.Persona;



@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
