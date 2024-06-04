package com.example.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "domicilio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Domicilio extends Base{

	@Column(name = "calle")
	private String calle;
	
	@Column(name = "numero")
	private int numero;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "fk_localidad")
	private Localidad localidad;
	
}
