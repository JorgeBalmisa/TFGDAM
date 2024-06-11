package com.example.main.service;

import java.util.List;

import com.example.main.model.Cliente;

public interface ClienteService extends BaseService<Cliente, Long> {

	List<Cliente> searchNombre(String filtro) throws Exception;

	List<Cliente> searchApellido(String filtro) throws Exception;

	List<Cliente> searchNotName(String filtro) throws Exception;

	List<Cliente> searchNombreEnding(String filtro) throws Exception;

	List<Cliente> searchDni(String filtro) throws Exception;
	
	List<Cliente> searchDomicilio_Loc_Den(String filtro) throws Exception;
	
	List<Cliente> searchDomicilio_Calle_Numero(String calle, Integer numero) throws Exception;

}
