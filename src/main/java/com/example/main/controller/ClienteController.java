package com.example.main.controller;

import com.example.main.model.Domicilio;
import com.example.main.service.servicesImpl.DomicilioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.main.model.Cliente;
import com.example.main.service.servicesImpl.ClienteServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/personas")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteServiceImpl> {

	@Autowired
	private DomicilioServiceImpl domicilioService;

	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Cliente cliente) {
		try {
			Domicilio domicilio = cliente.getDomicilio();
			Domicilio existingDomicilio = domicilioService.saveOrUpdate(domicilio);
			cliente.setDomicilio(existingDomicilio);
			Cliente savedCliente = servicio.save(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. " + e.getMessage() + " Inténtelo más tarde\"}");
		}
	}

	@GetMapping("/searchNombre")
	public ResponseEntity<?> searchNombre(@RequestParam String filtro) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.searchNombre(filtro));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
		}
	}

	@GetMapping("/searchApellido")
	public ResponseEntity<?> searchApellido(@RequestParam String filtro) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.searchApellido(filtro));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
		}
	}

	@GetMapping("/searchNotName")
	public ResponseEntity<?> searchNotName(@RequestParam String filtro) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.searchNotName(filtro));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
		}
	}

	@GetMapping("/searchNombreEnding")
	public ResponseEntity<?> searchNombreEnding(@RequestParam String filtro) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.searchNombreEnding(filtro));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
		}
	}
	
	@GetMapping("/searchDni")
	public ResponseEntity<?> searchDni(@RequestParam String filtro) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.searchDni(filtro));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
		}
	}
	
	@GetMapping("/searchByLocalidad")
    public ResponseEntity<?> searchByLocalidad(@RequestParam String localidad) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchDomicilio_Loc_Den(localidad));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/searchByDomicilio")
    public ResponseEntity<?> searchByDomicilio_Calle_Numero(@RequestParam String calle, @RequestParam int numero) {
        try {
            
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchDomicilio_Calle_Numero(calle, numero));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
