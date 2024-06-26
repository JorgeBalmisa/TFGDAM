package com.example.main.service.servicesImpl;

import com.example.main.model.Cliente;
import com.example.main.model.Domicilio;
import com.example.main.model.Localidad;
import com.example.main.repository.ClienteRepository;
import com.example.main.service.ClienteService;
import com.example.main.service.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;


@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long> implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
    private LocalidadService localidadService;

    private static final String LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    
    public ClienteServiceImpl(ClienteRepository personaRepository, LocalidadService localidadService) {
        super(personaRepository);
    }

	@Override
	public List<Cliente> searchNombre(String filtro) throws Exception {
		try {
			return clienteRepository.findByNombreContaining(filtro);

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
	public List<Cliente> searchApellido(String filtro) throws Exception {
		try {
			return clienteRepository.findByApellidoContaining(filtro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Cliente> searchNotName(String filtro) throws Exception {
		try {
			return clienteRepository.findByNombreIsNot(filtro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Cliente> searchNombreEnding(String filtro) throws Exception {
		try {
			return clienteRepository.findByNombreEndingWith(filtro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Cliente> searchDni(String filtro) throws Exception {
		return clienteRepository.findByDni(filtro);
	}

	@Override
	public List<Cliente> searchDomicilio_Loc_Den(String filtro) throws Exception {
		return clienteRepository.findByDomicilio_Localidad_Denominacion(filtro);
	}

	@Override
	public List<Cliente> searchDomicilio_Calle_Numero(String calle, Integer numero) throws Exception {
		return clienteRepository.findByDomicilio_CalleAndDomicilio_Numero(calle, numero);
	}

	@Transactional
	public Cliente updateCliente(Long clienteId, Cliente clienteActualiz) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

		cliente.setNombre(clienteActualiz.getNombre());
		cliente.setApellido(clienteActualiz.getApellido());
		cliente.setDni(clienteActualiz.getDni());

		return clienteRepository.save(cliente);
	}
}


