package com.example.main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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
	private Integer numero;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "localidad_id")
	private Localidad localidad;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Domicilio domicilio = (Domicilio) o;
		return numero == domicilio.numero &&
				Objects.equals(calle, domicilio.calle) &&
				Objects.equals(localidad, domicilio.localidad);
	}

	@Override
	public int hashCode() {
		return Objects.hash(calle, numero, localidad);
	}
}
