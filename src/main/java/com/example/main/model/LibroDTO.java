package com.example.main.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibroDTO extends Base{

	private Long id;
    private String titulo;
    private String genero;
    private Integer paginas;
    private Integer fecha;
    private List<Autor> autores;
    private String editorial;
    private Integer cantidad;
    private Long numeroDePrestamos;
}
