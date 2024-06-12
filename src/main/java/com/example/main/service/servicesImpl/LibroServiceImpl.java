package com.example.main.service.servicesImpl;

import com.example.main.model.Autor;
import com.example.main.model.Libro;
import com.example.main.repository.AutorRepository;
import com.example.main.repository.LibroRepository;
import com.example.main.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl extends BaseServiceImpl<Libro, Long> implements LibroService{

    @Autowired
	private LibroRepository libroRepository;

    @Autowired
	private AutorRepository autorRepository;

    @Autowired
    private AutorServiceImpl autorService;

    public LibroServiceImpl(LibroRepository libroRepository) {
        super(libroRepository);
    }

    /**
     * Guarda un libro y seguidamente guarda su(s) Autor(es) comprobando si son o no nuevos en la base de datos
     * @param libro
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Libro saveBookAndAuthors(Libro libro) throws Exception {
        try {
            if (!libro.getAutores().isEmpty()) {
                for (Autor autor : libro.getAutores()) {
                    Autor autorExistente = autorService.findByNombreAndApellido(autor.getNombre(), autor.getApellido());
                    if (autorExistente == null) {
                        autorExistente = autorService.save(autor);
                    }
                    libro.getAutores().add(autorExistente);
                }
            }

            return libroRepository.save(libro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Libro updateLibro(Long libroId, Libro libroActualiz) {
        Libro libro = libroRepository.findById(libroId).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.setTitulo(libroActualiz.getTitulo());
        libro.setGenero(libroActualiz.getGenero());
        libro.setPaginas(libroActualiz.getPaginas());
        libro.setFecha(libroActualiz.getFecha());
        libro.setEditorial(libroActualiz.getEditorial());
        libro.setCantidad(libroActualiz.getCantidad());

        return libroRepository.save(libro);
    }

    @Transactional
    public Libro addAutorALibro(Long libroId, Long autorId) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        if (!libro.getAutores().contains(autor)) {
            libro.getAutores().add(autor);
            autor.getLibros().add(libro);
        }

        return libroRepository.save(libro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> searchByTitulo(String filtro) throws Exception {
        return libroRepository.findByTitulo(filtro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> searchByGenero(String filtro) throws Exception {
        return libroRepository.findByGenero(filtro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> searchByAutores(String filtro) throws Exception {
        return libroRepository.findByAutores(filtro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> searchByEditorial(String filtro) throws Exception {
        return libroRepository.findByEditorial(filtro);
    }
}
