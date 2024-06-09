package com.example.main.controllerTest;

import com.example.main.controller.PersonaController;
import com.example.main.entities.Domicilio;
import com.example.main.entities.Localidad;
import com.example.main.entities.Persona;
import com.example.main.servicesImpl.PersonaServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonaController.class)
class PersonaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonaServiceImpl servicio;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAll_Success() throws Exception {
		// Arrange
		Localidad localidad = new Localidad();
		localidad.setDenominacion("Guarroman");

		Domicilio domicilio = new Domicilio();
		domicilio.setCalle("San Pepito");
		domicilio.setNumero(2);
		domicilio.setLocalidad(localidad);

		Persona persona1 = new Persona();
		persona1.setNombre("Pedro");
		persona1.setApellido("Martinez");
		persona1.setDni("12345678A");
		persona1.setDomicilio(domicilio);

		Persona persona2 = new Persona();
		persona2.setNombre("Juan");
		persona2.setApellido("Perez");
		persona2.setDni("87654321B");
		persona2.setDomicilio(domicilio);

		List<Persona> expectedResponse = Arrays.asList(persona1, persona2);
		when(servicio.findAll()).thenReturn(expectedResponse);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Pedro"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].nombre").value("Juan"));
	}

	@Test
	void testGetAll_Exception() throws Exception {
		// Arrange
		when(servicio.findAll()).thenThrow(new Exception("Error. Inténtelo más tarde"));

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/")).andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().string("{\"error\":\"Error. Inténtelo más tarde\"}"));
	}

	@Test
	void testGetOne_Success() throws Exception {
		// Arrange
		Localidad localidad = new Localidad();
		localidad.setDenominacion("Guarroman");

		Domicilio domicilio = new Domicilio();
		domicilio.setCalle("San Pepito");
		domicilio.setNumero(2);
		domicilio.setLocalidad(localidad);

		Persona persona = new Persona();
		persona.setNombre("Pedro");
		persona.setApellido("Martinez");
		persona.setDni("12345678A");
		persona.setDomicilio(domicilio);

		Long id = 1L;
		when(servicio.findById(id)).thenReturn(persona);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/{id}", id)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Pedro"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Martinez"));
	}

	@Test
	void testGetOne_NotFound() throws Exception {
		// Arrange
		Long id = 1L;
		when(servicio.findById(id)).thenReturn(null);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/{id}", id)).andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().string("{\"error\":\"Persona no encontrada\"}"));
	}

	@Test
	void testGetOne_Exception() throws Exception {
		// Arrange
		Long id = 1L;
		when(servicio.findById(id)).thenThrow(new Exception("Error. Inténtelo más tarde"));

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/{id}", id))
				.andExpect(status().isInternalServerError())
				.andExpect(MockMvcResultMatchers.content().string("{\"error\":\"Error. Inténtelo más tarde\"}"));
	}

	@Test
	void testDelete_Success() throws Exception {
		// Arrange
		Long id = 1L;
		when(servicio.delete(id)).thenReturn(true);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/personas/{id}", id)).andExpect(status().isNoContent());
	}

	@Test
	void testDelete_NotFound() throws Exception {
		// Arrange
		Long id = 1L;
		when(servicio.delete(id)).thenReturn(false);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/personas/{id}", id)).andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().string("{\"error\":\"Persona no encontrada\"}"));
	}

	@Test
	void testDelete_Exception() throws Exception {
		// Arrange
		Long id = 1L;
		when(servicio.delete(id)).thenThrow(new Exception("Error al eliminar la persona"));

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/personas/{id}", id))
				.andExpect(status().isInternalServerError())
				.andExpect(MockMvcResultMatchers.content().string("{\"error\":\"Error al eliminar la persona\"}"));
	}

}
