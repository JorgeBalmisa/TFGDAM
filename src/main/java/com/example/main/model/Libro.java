package com.example.main.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Libro extends Base {

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "genero")
    private String genero;

    @Column(name = "paginas")
    private Integer paginas;

    @Column(name = "fecha")
    private Integer fecha;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "libro_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<Autor>();

    @Column(name = "editorial")
    private String editorial;

    @Column(name = "stock_fisico")
    private String cantidad;

    @ManyToMany(mappedBy = "libros")
    private List<Prestamo> prestamos = new ArrayList<>();

}
