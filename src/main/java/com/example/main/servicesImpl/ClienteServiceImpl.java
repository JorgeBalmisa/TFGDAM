package com.example.main.servicesImpl;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.main.entities.Localidad;
import com.example.main.entities.Cliente;
import com.example.main.repositories.ClienteRepository;
import com.example.main.services.LocalidadService;
import com.example.main.services.ClienteService;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long> implements ClienteService {

	private final ClienteRepository PERSONAREPOSITORY;
    private final LocalidadService LOCALIDADSERVICE;

    private static final String LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    
    public ClienteServiceImpl(ClienteRepository personaRepository, LocalidadService localidadService) {
        super(personaRepository);
        this.PERSONAREPOSITORY = personaRepository;
        this.LOCALIDADSERVICE = localidadService;
    }

	@Override
	public List<Cliente> searchNombre(String filtro) throws Exception {
		try {
			return PERSONAREPOSITORY.findByNombreContaining(filtro);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Cliente save(Cliente persona) throws Exception {
		try {

			if (persona.getDni() == null || persona.getDni().isEmpty()) {
				String dni = generateDNI();
				persona.setDni(dni);
			}
			if (persona.getDomicilio() != null && persona.getDomicilio().getLocalidad() != null) {
				Localidad localidad = persona.getDomicilio().getLocalidad();
				// Crea la localidad si no ha sido creada aún
				localidad = LOCALIDADSERVICE.save(localidad);
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
	public List<Cliente> searchApellido(String filtro) throws Exception {
		try {
			return PERSONAREPOSITORY.findByApellidoContaining(filtro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Cliente> searchNotName(String filtro) throws Exception {
		try {
			return PERSONAREPOSITORY.findByNombreIsNot(filtro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Cliente> searchNombreEnding(String filtro) throws Exception {
		try {
			return PERSONAREPOSITORY.findByNombreEndingWith(filtro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Cliente> searchDni(String filtro) throws Exception {
		return PERSONAREPOSITORY.findByDni(filtro);
	}

	@Override
	public List<Cliente> searchDomicilio_Loc_Den(String filtro) throws Exception {
		return PERSONAREPOSITORY.findByDomicilio_Localidad_Denominacion(filtro);
	}

	@Override
	public List<Cliente> searchDomicilio_Calle_Numero(String calle, Integer numero) throws Exception {
		return PERSONAREPOSITORY.findByDomicilio_CalleAndDomicilio_Numero(calle, numero);
	}
	
	

}
