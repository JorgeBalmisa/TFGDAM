package com.example.main.services;

import org.springframework.stereotype.Service;

import com.example.main.entities.Localidad;
import com.example.main.repositories.BaseRepository;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Long> implements LocalidadService{

	public LocalidadServiceImpl(BaseRepository<Localidad, Long> baseRepository) {
		super(baseRepository);
	}

}
