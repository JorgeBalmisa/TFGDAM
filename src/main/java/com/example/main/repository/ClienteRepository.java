package com.example.main.repository;

import java.util.List;

import com.example.main.model.Domicilio;
import org.springframework.stereotype.Repository;

import com.example.main.model.Cliente;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {

	List<Cliente> findByNombreContaining(String nombre);

	List<Cliente> findByApellidoContaining(String apellido);

	List<Cliente> findByNombreIsNot(String nombre);

	List<Cliente> findByNombreEndingWith(String sufijo);

	List<Cliente> findByDni(String dni);

	List<Cliente> findByDomicilio_Localidad_Denominacion(String denominacion);

	List<Cliente> findByDomicilio_CalleAndDomicilio_Numero(String calle, int numero);

	List<Cliente> findByDomicilio(Domicilio domicilio);

}
