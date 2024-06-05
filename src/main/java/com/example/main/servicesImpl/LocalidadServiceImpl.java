package com.example.main.servicesImpl;

import org.springframework.stereotype.Service;

import com.example.main.entities.Localidad;
import com.example.main.repositories.BaseRepository;
import com.example.main.services.LocalidadService;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Long> implements LocalidadService{

	public LocalidadServiceImpl(BaseRepository<Localidad, Long> baseRepository) {
		super(baseRepository);
	}

}
