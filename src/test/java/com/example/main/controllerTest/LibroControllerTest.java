package com.example.main.controllerTest;

import com.example.main.controller.LibroController;
import com.example.main.model.Autor;
import com.example.main.model.Libro;
import com.example.main.service.servicesImpl.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroServiceImpl servicio;

    @Test
    void testSearchNombre_Success() throws Exception {
        // Arrange
        Libro libro1 = new Libro();
        libro1.setTitulo("El Quijote");
        libro1.setGenero("Novela");
        libro1.setPaginas(500);
        libro1.setFecha(1605);

        Libro libro2 = new Libro();
        libro2.setTitulo("Don Juan");
        libro2.setGenero("Novela");
        libro2.setPaginas(300);
        libro2.setFecha(1630);

        List<Libro> expectedResponse = Arrays.asList(libro1, libro2);
        when(servicio.searchByTitulo("Don")).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchTitulo")
                .param("filtro", "Don"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("El Quijote"))
                .andExpect(jsonPath("$[1].titulo").value("Don Juan"));
    }

    @Test
    void testSearchNombre_NotFound() throws Exception {
        // Arrange
        when(servicio.searchByTitulo("NoExistente")).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchTitulo")
                .param("filtro", "NoExistente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void testSearchNombre_Exception() throws Exception {
        // Arrange
        when(servicio.searchByTitulo("Error")).thenThrow(new RuntimeException("Simulated service exception"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchTitulo")
                .param("filtro", "Error"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"error\": \"Simulated service exception\"}"));
    }
    
    @Test
    void testSearchGenero_Success() throws Exception {
        // Arrange
        Libro libro1 = new Libro();
        libro1.setTitulo("El Quijote");
        libro1.setGenero("Novela");
        libro1.setPaginas(500);
        libro1.setFecha(1605);

        Libro libro2 = new Libro();
        libro2.setTitulo("Don Juan");
        libro2.setGenero("Novela");
        libro2.setPaginas(300);
        libro2.setFecha(1630);

        List<Libro> expectedResponse = Arrays.asList(libro1, libro2);
        when(servicio.searchByGenero("Novela")).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchGenero")
                .param("filtro", "Novela"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("El Quijote"))
                .andExpect(jsonPath("$[1].titulo").value("Don Juan"));
    }

    // Test for searchGenero - not found scenario
    @Test
    void testSearchGenero_NotFound() throws Exception {
        // Arrange
        when(servicio.searchByGenero("NoExistente")).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchGenero")
                .param("filtro", "NoExistente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // Test for searchGenero - exception scenario
    @Test
    void testSearchGenero_Exception() throws Exception {
        // Arrange
        when(servicio.searchByGenero("Error")).thenThrow(new RuntimeException("Simulated service exception"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchGenero")
                .param("filtro", "Error"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"error\": \"Simulated service exception\"}"));
    }
    
    @Test
    void testSearchAutores_Success() throws Exception {
        // Arrange
        Autor autor1 = new Autor();
        autor1.setNombre("Miguel de Cervantes");

        Autor autor2 = new Autor();
        autor2.setNombre("William Shakespeare");

        Libro libro1 = new Libro();
        libro1.setTitulo("El Quijote");
        libro1.setAutores(Arrays.asList(autor1));

        Libro libro2 = new Libro();
        libro2.setTitulo("Hamlet");
        libro2.setAutores(Arrays.asList(autor2));

        
        when(servicio.searchByAutores("Miguel de Cervantes")).thenReturn(Collections.singletonList(libro1));
        when(servicio.searchByAutores("William Shakespeare")).thenReturn(Collections.singletonList(libro2));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchAutores")
                .param("filtro", "Miguel de Cervantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].titulo").value("El Quijote"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchAutores")
                .param("filtro", "William Shakespeare"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Hamlet"));
    }

    // Test for searchAutores - not found scenario
    @Test
    void testSearchAutores_NotFound() throws Exception {
        // Arrange
        when(servicio.searchByAutores("NoExistente")).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchAutores")
                .param("filtro", "NoExistente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // Test for searchAutores - exception scenario
    @Test
    void testSearchAutores_Exception() throws Exception {
        // Arrange
        when(servicio.searchByAutores("Error")).thenThrow(new RuntimeException("Simulated service exception"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchAutores")
                .param("filtro", "Error"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"error\": \"Simulated service exception\"}"));
    }
    
    @Test
    void testSearchEditorial_Success() throws Exception {
        // Arrange
        Libro libro1 = new Libro();
        libro1.setTitulo("El Quijote");
        libro1.setEditorial("Editorial Planeta");

        Libro libro2 = new Libro();
        libro2.setTitulo("Cien Años de Soledad");
        libro2.setEditorial("Editorial Planeta");

        List<Libro> expectedResponse = Arrays.asList(libro1, libro2);
        when(servicio.searchByEditorial("Editorial Planeta")).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchEditorial")
                .param("filtro", "Editorial Planeta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("El Quijote"))
                .andExpect(jsonPath("$[1].titulo").value("Cien Años de Soledad"));
    }

    // Test for searchEditorial - not found scenario
    @Test
    void testSearchEditorial_NotFound() throws Exception {
        // Arrange
        when(servicio.searchByEditorial("NoExistente")).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchEditorial")
                .param("filtro", "NoExistente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // Test for searchEditorial - exception scenario
    @Test
    void testSearchEditorial_Exception() throws Exception {
        // Arrange
        when(servicio.searchByEditorial("Error")).thenThrow(new RuntimeException("Simulated service exception"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/libros/searchEditorial")
                .param("filtro", "Error"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"error\": \"Simulated service exception\"}"));
    }
}

