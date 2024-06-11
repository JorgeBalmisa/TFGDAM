package com.example.main.service.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.model.Autor;
import com.example.main.repository.AutorRepository;
import com.example.main.repository.BaseRepository;
import com.example.main.service.AutorService;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Long> implements AutorService{

	@Autowired
	private AutorRepository autorRepository;
	
	public AutorServiceImpl(BaseRepository<Autor, Long> baseRepository) {
		super(baseRepository);
	}

	public Autor findByNombreAndApellido(String nombre, String apellido) {
		try {
			return autorRepository.findByNombreAndApellido(nombre, apellido);
		} catch (Exception e) {
			throw new RuntimeException("Error al intentar encontrar el autor por nombre y apellido: " + e.getMessage());
		}
	}

}
