package com.example.main.service.servicesImpl;

import com.example.main.model.Cliente;
import com.example.main.model.Libro;
import com.example.main.model.Prestamo;
import com.example.main.repository.BaseRepository;
import com.example.main.repository.ClienteRepository;
import com.example.main.repository.LibroRepository;
import com.example.main.repository.PrestamoRepository;
import com.example.main.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoServiceImpl extends BaseServiceImpl<Prestamo, Long> implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LibroRepository libroRepository;

    public PrestamoServiceImpl(BaseRepository<Prestamo, Long> baseRepository) {
        super(baseRepository);
    }

    @Transactional
    public Prestamo createPrestamo(Long clienteId, List<Long> libroIds, Prestamo prestamo) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        for (Long libroId : libroIds) {
            Libro libro = libroRepository.findById(libroId)
                    .orElseThrow(() -> new RuntimeException("Libro con ID " + libroId + " no encontrado"));

            if (libro.getCantidad() > 0) {
                libro.setCantidad(libro.getCantidad() - 1);
                prestamo.getLibros().add(libro);
                libro.getPrestamos().add(prestamo);
            } else {
                throw new RuntimeException("No hay ejemplares disponibles para el libro con ID: " + libroId);
            }
        }

        prestamo.setCliente(cliente);
        cliente.getPrestamos().add(prestamo);

        return prestamoRepository.save(prestamo);
    }

    @Transactional(readOnly = true)
    public List<Prestamo> findPrestamosByClienteNombre(String nombre) {
        return prestamoRepository.findByClienteNombre(nombre);
    }

    @Transactional(readOnly = true)
    public List<Prestamo> findPrestamosByLibroNombre(String nombre) {
        return prestamoRepository.findByLibrosNombre(nombre);
    }

    @Transactional
    public Prestamo updatePrestamoFechaDevolucion(Long prestamoId, String nuevaFecha) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado"));

        // Comprueba si la fecha de devolución es actualmente nula y la nueva no es nula
        if (prestamo.getFechaDevolucion() == null && nuevaFecha != null) {
            for (Libro libro : prestamo.getLibros()) {
                libro.setCantidad(libro.getCantidad() + 1);  // Incrementa la cantidad de libros ya que el prestamo se cierra
                libroRepository.save(libro);  // Guarda los cambios en el libro
            }
        }

        prestamo.setFechaDevolucion(nuevaFecha);
        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public void deletePrestamo(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado"));

        Cliente cliente = prestamo.getCliente();
        cliente.getPrestamos().remove(prestamo);

        prestamo.getLibros().forEach(libro -> {
            libro.getPrestamos().remove(prestamo);
            libro.setCantidad(libro.getCantidad() + 1);
        });

        prestamoRepository.delete(prestamo);
    }
}
