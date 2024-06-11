package com.example.main.service.servicesImpl;

import com.example.main.model.Autor;
import com.example.main.model.Libro;
import com.example.main.repository.LibroRepository;
import com.example.main.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroServiceImpl extends BaseServiceImpl<Libro, Long> implements LibroService{

    @Autowired
	private LibroRepository libroRepository;

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
                    Autor existingAutor = autorService.findByNombreAndApellido(autor.getNombre(), autor.getApellido());
                    if (existingAutor == null) {
                        existingAutor = autorService.save(autor);
                    }
                    libro.getAutores().add(existingAutor);
                }
            }

            return libroRepository.save(libro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Libro> searchByTitulo(String filtro) throws Exception {
        return libroRepository.findByTitulo(filtro);
    }

    @Override
    public List<Libro> searchByGenero(String filtro) throws Exception {
        return libroRepository.findByGenero(filtro);
    }

    @Override
    public List<Libro> searchByAutores(String filtro) throws Exception {
        return libroRepository.findByAutores(filtro);
    }

    @Override
    public List<Libro> searchByEditorial(String filtro) throws Exception {
        return libroRepository.findByEditorial(filtro);
    }
}
