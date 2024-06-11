package com.example.main.service.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.model.Libro;
import com.example.main.repository.LibroRepository;
import com.example.main.service.LibroService;

@Service
public class LibroServiceImpl extends BaseServiceImpl<Libro, Long> implements LibroService{

	private final LibroRepository LIBROREPOSITORY;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository) {
        super(libroRepository);
        this.LIBROREPOSITORY = libroRepository;
    }
}
