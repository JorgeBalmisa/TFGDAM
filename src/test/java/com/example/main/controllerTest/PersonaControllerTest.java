package com.example.main.controllerTest;

import com.example.main.controller.ClienteController;
import com.example.main.model.Domicilio;
import com.example.main.model.Localidad;
import com.example.main.model.Cliente;
import com.example.main.service.servicesImpl.ClienteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClienteController.class)
class PersonaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ClienteServiceImpl servicio;

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

		Cliente persona1 = new Cliente();
		persona1.setNombre("Pedro");
		persona1.setApellido("Martinez");
		persona1.setDni("12345678A");
		persona1.setDomicilio(domicilio);

		Cliente persona2 = new Cliente();
		persona2.setNombre("Juan");
		persona2.setApellido("Perez");
		persona2.setDni("87654321B");
		persona2.setDomicilio(domicilio);

		List<Cliente> expectedResponse = Arrays.asList(persona1, persona2);
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

		Cliente persona = new Cliente();
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

	@Test
	void testSave_Success() throws Exception {
		// Arrange
		Localidad localidad = new Localidad();
		localidad.setDenominacion("Guarroman");

		Domicilio domicilio = new Domicilio();
		domicilio.setCalle("San Pepito");
		domicilio.setNumero(2);
		domicilio.setLocalidad(localidad);

		Cliente persona = new Cliente();
		persona.setNombre("Pedro");
		persona.setApellido("Martinez");
		persona.setDni("12345678A");
		persona.setDomicilio(domicilio);

		when(servicio.save(any())).thenReturn(persona);

		// Act & Assert
		mockMvc.perform(post("/api/v1/personas/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(persona))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.nombre").value("Pedro")).andExpect(jsonPath("$.apellido").value("Martinez"))
				.andExpect(jsonPath("$.dni").value("12345678A"))
				.andExpect(jsonPath("$.domicilio.calle").value("San Pepito"))
				.andExpect(jsonPath("$.domicilio.numero").value(2))
				.andExpect(jsonPath("$.domicilio.localidad.denominacion").value("Guarroman"));
	}

	@Test
	void testSave_Exception() throws Exception {
		// Arrange
		Localidad localidad = new Localidad();
		localidad.setDenominacion("Guarroman");

		Domicilio domicilio = new Domicilio();
		domicilio.setCalle("San Pepito");
		domicilio.setNumero(2);
		domicilio.setLocalidad(localidad);

		Cliente persona = new Cliente();
		persona.setNombre("Pedro");
		persona.setApellido("Martinez");
		persona.setDni("12345678A");
		persona.setDomicilio(domicilio);

		when(servicio.save(any())).thenThrow(new Exception("Error al guardar la persona"));

		// Act & Assert
		mockMvc.perform(post("/api/v1/personas/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(persona))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Error. Error al guardar la persona Inténtelo más tarde"));
	}

	@Test
	void testUpdate_Success() throws Exception {
		// Arrange
		Long id = 1L;
		Cliente updatedPersona = new Cliente();
		updatedPersona.setId(id); // Assuming setId exists in Persona class
		updatedPersona.setNombre("UpdatedName");
		updatedPersona.setApellido("UpdatedApellido");
		updatedPersona.setDni("12345678A");

		when(servicio.update(any(Long.class), any(Cliente.class))).thenReturn(updatedPersona);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/personas/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content("{ \"nombre\": \"UpdatedName\", \"apellido\": \"UpdatedApellido\", \"dni\": \"12345678A\" }"))
				.andExpect(status().isOk());
	}

	@Test
	void testUpdate_Exception() throws Exception {
		// Arrange
		Long id = 1L;
		when(servicio.update(any(Long.class), any(Cliente.class)))
				.thenThrow(new Exception("Error al actualizar la persona"));

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/personas/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content("{ \"nombre\": \"UpdatedName\", \"apellido\": \"UpdatedApellido\", \"dni\": \"12345678A\" }"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testSearchNombre_Success() throws Exception {
		// Arrange
		String filtro = "Pedro";
		Cliente persona = new Cliente();
		persona.setNombre("Pedro");
		List<Cliente> personas = new ArrayList<>();
		personas.add(persona);
		when(servicio.searchNombre(filtro)).thenReturn(personas);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchNombre").param("filtro", filtro))
				.andExpect(status().isOk());
	}

	@Test
	void testSearchNombre_Exception() throws Exception {
		// Arrange
		String filtro = "Juan";
		when(servicio.searchNombre(filtro)).thenThrow(new Exception("Persona no encontrada"));

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchNombre").param("filtro", filtro))
				.andExpect(status().isNotFound());
	}

	@Test
	void testSearchApellido_Success() throws Exception {
		// Arrange
		String filtro = "Martinez";
		Cliente persona = new Cliente();
		persona.setApellido("Martinez");
		List<Cliente> personas = new ArrayList<>();
		personas.add(persona);
		when(servicio.searchApellido(filtro)).thenReturn(personas);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchApellido").param("filtro", filtro))
				.andExpect(status().isOk());
	}

	@Test
	void testSearchApellido_Exception() throws Exception {
		// Arrange
		String filtro = "Gonzalez";
		when(servicio.searchApellido(filtro)).thenThrow(new Exception("Persona no encontrada"));

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchApellido").param("filtro", filtro))
				.andExpect(status().isNotFound());
	}

	@Test
	void testSearchNotName_Success() throws Exception {
		// Arrange
		String filtro = "Martinez";
		Cliente persona = new Cliente();
		persona.setNombre("Juan");
		List<Cliente> personas = new ArrayList<>();
		personas.add(persona);
		when(servicio.searchNotName(filtro)).thenReturn(personas);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchNotName").param("filtro", filtro))
				.andExpect(status().isOk());
	}

	@Test
	void testSearchNotName_Exception() throws Exception {
		// Arrange
		String filtro = "Gonzalez";
		when(servicio.searchNotName(filtro)).thenThrow(new Exception("Persona no encontrada"));

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchNotName").param("filtro", filtro))
				.andExpect(status().isNotFound());
	}

	@Test
	void testSearchNombreEnding_Success() throws Exception {
		// Arrange
		String filtro = "ez";
		Cliente persona = new Cliente();
		persona.setNombre("Lopez");
		List<Cliente> personas = new ArrayList<>();
		personas.add(persona);
		when(servicio.searchNombreEnding(filtro)).thenReturn(personas);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchNombreEnding").param("filtro", filtro))
				.andExpect(status().isOk());
	}

	@Test
	void testSearchNombreEnding_Exception() throws Exception {
		// Arrange
		String filtro = "Gonzalez";
		when(servicio.searchNombreEnding(filtro)).thenThrow(new Exception("Persona no encontrada"));

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchNombreEnding").param("filtro", filtro))
				.andExpect(status().isNotFound());
	}
	
	@Test
    void testSearchDni_Success() throws Exception {
        // Arrange
        String filtro = "12345678A";
        Cliente persona = new Cliente();
        persona.setNombre("Pedro");
        persona.setApellido("Martinez");
        persona.setDni(filtro);
        List<Cliente> personas = new ArrayList<>();
        personas.add(persona);
        when(servicio.searchDni(filtro)).thenReturn(personas);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchDni")
                .param("filtro", filtro))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchDni_Exception() throws Exception {
        // Arrange
        String filtro = "87654321B";
        when(servicio.searchDni(filtro)).thenThrow(new Exception("Persona no encontrada"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchDni")
                .param("filtro", filtro))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void testSearchByLocalidad_Success() throws Exception {
        // Arrange
        String localidad = "Guarroman";
        Cliente persona = new Cliente();
        persona.setNombre("Pedro");
        persona.setApellido("Martinez");
        persona.setDni("12345678A");
        List<Cliente> personas = new ArrayList<>();
        personas.add(persona);
        when(servicio.searchDomicilio_Loc_Den(localidad)).thenReturn(personas);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchByLocalidad")
                .param("localidad", localidad))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchByLocalidad_Exception() throws Exception {
        // Arrange
        String localidad = "Guarroman";
        when(servicio.searchDomicilio_Loc_Den(localidad)).thenThrow(new Exception("Error al buscar personas"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchByLocalidad")
                .param("localidad", localidad))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSearchByDomicilio_Success() throws Exception {
        // Arrange
        String calle = "San Pepito";
        int numero = 2;
        Cliente persona = new Cliente();
        persona.setNombre("Pedro");
        persona.setApellido("Martinez");
        persona.setDni("12345678A");
        List<Cliente> personas = new ArrayList<>();
        personas.add(persona);
        when(servicio.searchDomicilio_Calle_Numero(calle, numero)).thenReturn(personas);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchByDomicilio")
                .param("calle", calle)
                .param("numero", String.valueOf(numero)))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchByDomicilio_Exception() throws Exception {
        // Arrange
        String calle = "San Pepito";
        int numero = 2;
        when(servicio.searchDomicilio_Calle_Numero(calle, numero)).thenThrow(new Exception("Error al buscar personas"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/personas/searchByDomicilio")
                .param("calle", calle)
                .param("numero", String.valueOf(numero)))
                .andExpect(status().isNotFound());
    }

}
