package com.example.main.service.servicesImpl;

import com.example.main.model.Libro;
import com.example.main.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.model.Autor;
import com.example.main.repository.AutorRepository;
import com.example.main.repository.BaseRepository;
import com.example.main.service.AutorService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Long> implements AutorService{

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LibroRepository libroRepository;

	public AutorServiceImpl(BaseRepository<Autor, Long> baseRepository) {
		super(baseRepository);
	}

	@Transactional
	public Autor createAutor(Autor autor) {
		return autorRepository.save(autor);
	}

	@Transactional(readOnly = true)
	public Autor findByNombreAndApellido(String nombre, String apellido) {
		try {
			return autorRepository.findByNombreAndApellido(nombre, apellido);
		} catch (Exception e) {
			throw new RuntimeException("Error al intentar encontrar el autor por nombre y apellido: " + e.getMessage());
		}
	}

	@Transactional
	public Autor updateAutor(Long autorId, Autor nuevoAutor) {
		Autor autor = autorRepository.findById(autorId).orElseThrow(() -> new RuntimeException("Autor no encontrado"));

		autor.setNombre(nuevoAutor.getNombre());
		autor.setApellido(nuevoAutor.getApellido());
		autor.setNacionalidad(nuevoAutor.getNacionalidad());
		autor.setBiografia(nuevoAutor.getBiografia());

		return autorRepository.save(autor);
	}

	@Transactional
	public void deleteAutor(Long autorId) {
		Optional<Autor> autorOptional = autorRepository.findById(autorId);
		if (autorOptional.isPresent()) {
			Autor autor = autorOptional.get();
			for (Libro libro : autor.getLibros()) {
				libro.getAutores().remove(autor);
				libroRepository.save(libro);
			}
			autorRepository.delete(autor);
		}
	}

}
