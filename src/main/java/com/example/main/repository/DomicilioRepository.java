package com.example.main.repository;

import com.example.main.model.Domicilio;
import com.example.main.model.Localidad;

import java.util.Optional;

public interface DomicilioRepository extends BaseRepository<Domicilio, Long>{
    Optional<Domicilio> findByCalleAndNumeroAndLocalidad(String calle, Integer numero, Localidad localidad);

}
