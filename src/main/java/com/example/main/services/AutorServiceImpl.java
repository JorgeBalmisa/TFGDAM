package com.example.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.entities.Autor;
import com.example.main.repositories.AutorRepository;
import com.example.main.repositories.BaseRepository;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Long> implements AutorService{

	@Autowired
	private AutorRepository autorRepository;
	
	public AutorServiceImpl(BaseRepository<Autor, Long> baseRepository) {
		super(baseRepository);
	}
}
