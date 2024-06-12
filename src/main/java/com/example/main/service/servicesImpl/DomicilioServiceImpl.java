package com.example.main.service.servicesImpl;

import com.example.main.model.Cliente;
import com.example.main.model.Domicilio;
import com.example.main.model.Localidad;
import com.example.main.repository.BaseRepository;
import com.example.main.repository.ClienteRepository;
import com.example.main.repository.DomicilioRepository;
import com.example.main.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio, Long> implements DomicilioService {

    public DomicilioServiceImpl(BaseRepository<Domicilio, Long> baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private LocalidadServiceImpl localidadService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public Domicilio findByCalleAndNumeroAndLocalidad(String calle, Integer numero, Localidad localidad) {
        return domicilioRepository.findByCalleAndNumeroAndLocalidad(calle, numero, localidad).orElse(null);
    }

    @Transactional
    public Domicilio saveOrUpdate(Domicilio domicilio) {
        Localidad localidad = localidadService.saveOrUpdate(domicilio.getLocalidad());
        Domicilio existingDomicilio = findByCalleAndNumeroAndLocalidad(domicilio.getCalle(), domicilio.getNumero(), localidad);

        if (existingDomicilio != null) {
            return existingDomicilio;
        } else {
            domicilio.setLocalidad(localidad);
            return domicilioRepository.save(domicilio);
        }
    }

    @Transactional
    public Domicilio updateDomicilio(Long clienteId, Long domicilioId, Domicilio domicilioNuevo) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Domicilio existente = domicilioRepository.findById(domicilioId)
                .orElseThrow(() -> new RuntimeException("Domicilio no encontrado"));

        // Verificar si el domicilio está asociado a otros clientes
        List<Cliente> clientesConMismoDomicilio = clienteRepository.findByDomicilio(existente);

        Domicilio domicilioToUpdate;
        if (clientesConMismoDomicilio.size() > 1) {
            // Crear un nuevo domicilio para el cliente actual
            domicilioToUpdate = new Domicilio();
            domicilioToUpdate.setCalle(domicilioNuevo.getCalle());
            domicilioToUpdate.setNumero(domicilioNuevo.getNumero());
            domicilioToUpdate.setLocalidad(domicilioNuevo.getLocalidad());
            domicilioRepository.save(domicilioToUpdate);
        } else {
            // Actualizar el domicilio existente
            existente.setCalle(domicilioNuevo.getCalle());
            existente.setNumero(domicilioNuevo.getNumero());
            domicilioToUpdate = domicilioRepository.save(existente);
        }

        // Asociar el domicilio actualizado o nuevo al cliente
        cliente.setDomicilio(domicilioToUpdate);
        clienteRepository.save(cliente);

        return domicilioToUpdate;
    }
}
