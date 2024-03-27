package com.trabajo.integrador;

import com.trabajo.integrador.entity.Odontologo;
import com.trabajo.integrador.entity.Paciente;
import com.trabajo.integrador.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PacienteServiceTest {
    private PacienteService service;
    @Autowired
    public PacienteServiceTest(PacienteService service) {
        this.service = service;
    }

    @Test
    public void guardar () throws Exception {
        Paciente paciente =new Paciente("CARLOS","PEREZ","AV 7","52796");
        paciente=service.agregar(paciente);
        Assertions.assertNotNull(paciente);
    }
    @Test
    public void listar() throws Exception {
        Paciente paciente =new Paciente("CARLOS","PEREZ","AV 7","42796");
        paciente=service.agregar(paciente);
        Assertions.assertNotNull(paciente);
        List<Paciente> pacientes=service.listar();
        Assertions.assertNotNull(pacientes);
        Assertions.assertFalse(pacientes.isEmpty());
    }
    @Test
    public void eliminar() throws Exception {
        Paciente paciente =new Paciente("CARLOS","PEREZ","AV 7","32796");
        paciente=service.agregar(paciente);
        Assertions.assertNotNull(paciente);
        Assertions.assertTrue(service.eliminar("32796"));
    }

    @Test
    public void modificar() throws Exception {
        Paciente paciente =new Paciente("CAMILO","PEREZ","AV 9","22796");
        paciente=service.agregar(paciente);
        Assertions.assertNotNull(paciente);

        String nuevoNombre="ALAN";
        paciente.setNombre(nuevoNombre);
        paciente=service.modificar(paciente);
        Assertions.assertEquals(nuevoNombre,paciente.getNombre());
    }

    @Test
    public void buscarPorId() throws Exception {
        Paciente paciente =new Paciente("CAMILO","PEREZ","AV 9","26796");
        paciente=service.agregar(paciente);
        paciente=service.buscarPorId(paciente.getDni());
        Assertions.assertNotNull(paciente);
    }
}
