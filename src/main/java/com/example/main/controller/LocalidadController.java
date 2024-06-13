package com.example.main.controller;

import com.example.main.model.Localidad;
import com.example.main.service.servicesImpl.LocalidadServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/localidades")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadServiceImpl> {

	@Override
	@PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Localidad updatedLocalidad) {
        try {
            Localidad localidad = servicio.findById(id);
            if (localidad == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Actualizar los campos relevantes de la entidad existente
            localidad.setDenominacion(updatedLocalidad.getDenominacion());
            // Puedes agregar más campos aquí según tu entidad
            
            // Guardar la entidad actualizada
            Localidad updatedEntity = servicio.save(localidad);
            
            return ResponseEntity.ok(updatedEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al actualizar la entidad\"}");
        }
    }

}
