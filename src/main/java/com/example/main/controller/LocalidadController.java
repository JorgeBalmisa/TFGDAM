package com.example.main.controller;

import com.example.main.model.Localidad;
import com.example.main.service.servicesImpl.LocalidadServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/localidades")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadServiceImpl> {

    @PutMapping("/update/{localidadId}/domicilio/{domicilioId}")
    public ResponseEntity<?> update(@PathVariable Long localidadId, @PathVariable Long domicilioId,
                                                     @RequestBody String nuevaDenom) {
        Localidad updatedLocalidad = servicio.updateLocalidad(domicilioId, localidadId, nuevaDenom);
        return ResponseEntity.ok(updatedLocalidad);
    }

}
