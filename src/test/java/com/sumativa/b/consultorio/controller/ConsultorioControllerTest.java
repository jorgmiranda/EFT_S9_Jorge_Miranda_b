package com.sumativa.b.consultorio.controller;

import java.util.List;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sumativa.b.consultorio.model.Paciente;
import com.sumativa.b.consultorio.service.AtencionMedicaServiceImpl;
import com.sumativa.b.consultorio.service.ConsultaMedicaServiceImpl;
import com.sumativa.b.consultorio.service.PacienteServiceImpl;

@WebMvcTest(ConsultorioController.class)
public class ConsultorioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteServiceImpl pacienteServiceImpl;

    @MockBean
    private ConsultaMedicaServiceImpl consultaMedicaServiceImpl;

    @MockBean
    private AtencionMedicaServiceImpl atencionMedicaServiceImpl;

    @Test
    public void getAllPaciente() throws Exception{
        //Arrange
        //Creacion de Pacientes
        Paciente paciente1 = new Paciente();
        paciente1.setRut("9999999-9");
        paciente1.setNombre("Miguelito Manolo");
        paciente1.setCorreo("miguelito.manolo@prueba.cl");
        paciente1.setDireccion("Calle prueba 123, el examen");

        Paciente paciente2 = new Paciente();
        paciente2.setRut("8888888-8");
        paciente2.setNombre("María González");
        paciente2.setCorreo("maria.gonzalez@ejemplo.com");
        paciente2.setDireccion("Avenida Principal 456, Ciudad Ejemplo");

        List<Paciente> pacientes = Arrays.asList(paciente1, paciente2);
        when(pacienteServiceImpl.getAllPaciente()).thenReturn(pacientes);

        //ACT & Assert
         mockMvc.perform(MockMvcRequestBuilders.get("/pacientes"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[0].nombre", Matchers.is("Miguelito Manolo")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[0].rut", Matchers.is("9999999-9")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[0].correo", Matchers.is("miguelito.manolo@prueba.cl")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[0].direccion", Matchers.is("Calle prueba 123, el examen")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[1].rut", Matchers.is("8888888-8")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[1].nombre", Matchers.is("María González")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[1].correo", Matchers.is("maria.gonzalez@ejemplo.com")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[1].direccion", Matchers.is("Avenida Principal 456, Ciudad Ejemplo")));

    }
}
