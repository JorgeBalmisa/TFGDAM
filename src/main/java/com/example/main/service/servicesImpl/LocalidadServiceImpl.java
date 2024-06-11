package com.example.main.service.servicesImpl;

import com.example.main.model.Localidad;
import com.example.main.repository.BaseRepository;
import com.example.main.repository.LocalidadRepository;
import com.example.main.service.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Long> implements LocalidadService{

	@Autowired
	private LocalidadRepository localidadRepository;

	public LocalidadServiceImpl(BaseRepository<Localidad, Long> baseRepository) {
		super(baseRepository);
	}

	@Transactional(readOnly = true)
	public Localidad findByDenominacion(String nombre) {
		return localidadRepository.findByDenominacion(nombre).orElse(null);
	}

	@Transactional//(propagation = Propagation.REQUIRES_NEW)
	public Localidad saveOrUpdate(Localidad localidad) {
		Localidad existingLocalidad = findByDenominacion(localidad.getDenominacion());
		if (existingLocalidad != null) {
			return existingLocalidad;
		} else {
			return localidadRepository.save(localidad);
		}
	}
}
