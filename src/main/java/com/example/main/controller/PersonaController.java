package com.example.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.entities.Persona;
import com.example.main.servicesImpl.PersonaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/personas")
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl> {

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
