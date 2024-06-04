package com.example.main.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Libro extends Base{

	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "paginas")
	private int paginas;
	
	@Column(name = "fecha")
	private int fecha;
	
	@ManyToMany(cascade = CascadeType.REFRESH)
	private List<Autor> autores = new ArrayList<Autor>();
}
