package com.example.main.repository;

import com.example.main.model.Prestamo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends BaseRepository<Prestamo, Long> {

    List<Prestamo> findByClienteNombre(String nombre);

    @Query("SELECT p FROM Prestamo p JOIN p.libros l WHERE l.titulo LIKE %:nombre%")
    List<Prestamo> findByLibrosNombre(String nombre);

}
