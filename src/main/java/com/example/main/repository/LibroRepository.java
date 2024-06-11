package com.example.main.repository;

import com.example.main.model.Libro;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends BaseRepository<Libro, Long>{

    List<Libro> findByTitulo(String titulo);

    List<Libro> findByGenero(String genero);

    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE LOWER(a.nombre) = LOWER(:nombreAutor)")
    List<Libro> findByAutores(String nombreAutor);

    List<Libro> findByEditorial(String nombre);

}
