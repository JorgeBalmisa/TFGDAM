package com.example.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "localidad")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Localidad extends Base{
	
	@Column(name = "denominacion")
	private String denominacion;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Localidad localidad = (Localidad) o;
		return Objects.equals(denominacion, localidad.denominacion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(denominacion);
	}
}
