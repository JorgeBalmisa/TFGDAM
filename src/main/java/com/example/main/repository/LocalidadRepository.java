package com.example.main.repository;

import org.springframework.stereotype.Repository;

import com.example.main.model.Localidad;

import java.util.Optional;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad, Long>{
    Optional<Localidad> findByDenominacion(String Nombre);

}
