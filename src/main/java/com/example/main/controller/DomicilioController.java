package com.example.main.controller;

import com.example.main.model.Domicilio;
import com.example.main.service.servicesImpl.DomicilioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/domicilios")
public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioServiceImpl> {

    @PutMapping("/update/{domicilioId}/cliente/{clienteId}")
    public ResponseEntity<?> update(@PathVariable Long domicilioId, @PathVariable Long clienteId,
                                    @RequestBody Domicilio domicilioActualiz) {
        Domicilio actualizar = servicio.updateDomicilio(clienteId, domicilioId, domicilioActualiz);
        return ResponseEntity.ok(actualizar);
    }
}
