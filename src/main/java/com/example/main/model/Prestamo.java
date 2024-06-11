package com.example.main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prestamo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Prestamo extends Base {

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "prestamo_libro",
            joinColumns = @JoinColumn(name = "prestamo_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    private List<Libro> libros = new ArrayList<>();

    @Column(name = "fecha_prestamo")
    private String fechaPrestamo;

    @Column(name = "fecha_devolucion")
    private String fechaDevolucion;


}
