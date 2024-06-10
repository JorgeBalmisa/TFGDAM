package com.example.main.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.entities.Libro;
import com.example.main.repositories.LibroRepository;
import com.example.main.services.LibroService;

@Service
public class LibroServiceImpl extends BaseServiceImpl<Libro, Long> implements LibroService{

	private final LibroRepository LIBROREPOSITORY;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository) {
        super(libroRepository);
        this.LIBROREPOSITORY = libroRepository;
    }
}
