package com.example.main.service.servicesImpl;

import com.example.main.model.Domicilio;
import com.example.main.model.Localidad;
import com.example.main.repository.BaseRepository;
import com.example.main.repository.DomicilioRepository;
import com.example.main.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio, Long> implements DomicilioService {

    public DomicilioServiceImpl(BaseRepository<Domicilio, Long> baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private LocalidadServiceImpl localidadService;

    @Transactional(readOnly = true)
    public Domicilio findByCalleAndNumeroAndLocalidad(String calle, Integer numero, Localidad localidad) {
        return domicilioRepository.findByCalleAndNumeroAndLocalidad(calle, numero, localidad).orElse(null);
    }

    @Transactional//(propagation = Propagation.NESTED)
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
}
