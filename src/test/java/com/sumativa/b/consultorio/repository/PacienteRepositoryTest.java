package com.sumativa.b.consultorio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sumativa.b.consultorio.model.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PacienteRepositoryTest {
    @Autowired
    private PacienteRepository pacienteRepository;

    @SuppressWarnings("deprecation")
    @Test
    public void crearPaciente(){
        //Arrange
        Paciente paciente = new Paciente();
        paciente.setRut("7777777-7");
        paciente.setNombre("Juan Pérez");
        paciente.setCorreo("juan.perez@example.com");
        paciente.setDireccion("Calle Secundaria 789, Pueblo Ejemplo");
        paciente.setFechaNacimeinto(new Date(1985, 10, 15));

        //ACT
        Paciente resultado = pacienteRepository.save(paciente);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals("7777777-7", resultado.getRut());
        assertEquals("Juan Pérez", resultado.getNombre());
        assertEquals("juan.perez@example.com", resultado.getCorreo());
        assertEquals("Calle Secundaria 789, Pueblo Ejemplo", resultado.getDireccion());
        assertEquals(new Date(1985, 10, 15), resultado.getFechaNacimeinto());
    }
}
