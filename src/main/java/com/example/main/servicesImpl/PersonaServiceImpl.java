package com.example.main.servicesImpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.main.entities.Localidad;
import com.example.main.entities.Persona;
import com.example.main.repositories.BaseRepository;
import com.example.main.repositories.PersonaRepository;
import com.example.main.services.LocalidadService;
import com.example.main.services.PersonaService;

import jakarta.transaction.Transactional;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private LocalidadService localidadService;

	private static final String LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

	public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository) {
		super(baseRepository);
	}

	@Override
	public List<Persona> searchNombre(String filtro) throws Exception {
		try {
			return personaRepository.findByNombreContaining(filtro);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Persona save(Persona persona) throws Exception {
		try {

			if (persona.getDni() == null || persona.getDni().isEmpty()) {
				String dni = generateDNI();
				persona.setDni(dni);
			}
			if (persona.getDomicilio() != null && persona.getDomicilio().getLocalidad() != null) {
				Localidad localidad = persona.getDomicilio().getLocalidad();
				// Crear la localidad si no ha sido creada aún
				localidad = localidadService.save(localidad);
				persona.getDomicilio().setLocalidad(localidad);
			}
			return super.save(persona);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private String generateDNI() {
		Random random = new Random();
		int number = random.nextInt(90000000) + 10000000; // Genera un número de 8 dígitos
		char letter = LETTERS.charAt(number % 23); // Calcula la letra del DNI
		return number + String.valueOf(letter);
	}

	@Override
	public List<Persona> searchApellido(String filtro) throws Exception {
		try {
			return personaRepository.findByApellidoContaining(filtro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Persona> searchNotName(String filtro) throws Exception {
		try {
			return personaRepository.findByNombreIsNot(filtro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Persona> searchNombreEnding(String filtro) throws Exception {
		try {
			return personaRepository.findByNombreEndingWith(filtro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Persona> searchDni(String filtro) throws Exception {
		return personaRepository.findByDni(filtro);
	}

	@Override
	public List<Persona> searchDomicilio_Loc_Den(String filtro) throws Exception {
		return personaRepository.findByDomicilio_Localidad_Denominacion(filtro);
	}

	@Override
	public List<Persona> searchDomicilio_Calle_Numero(String calle, Integer numero) throws Exception {
		return personaRepository.findByDomicilio_CalleAndDomicilio_Numero(calle, numero);
	}
	
	

}
