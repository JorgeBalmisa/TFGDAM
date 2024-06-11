package com.example.main.service;

import com.example.main.model.Libro;

import java.util.List;

public interface LibroService extends BaseService<Libro, Long>{

    Libro saveBookAndAuthors(Libro libro) throws Exception;

    List<Libro> searchByTitulo(String filtro) throws Exception;

    List<Libro> searchByGenero(String filtro) throws Exception;

    List<Libro> searchByAutores(String filtro) throws Exception;

    List<Libro> searchByEditorial(String filtro) throws Exception;
}
