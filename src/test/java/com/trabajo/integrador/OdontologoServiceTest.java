package com.trabajo.integrador;

import com.trabajo.integrador.entity.Odontologo;
import com.trabajo.integrador.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OdontologoServiceTest {
    private OdontologoService service;

    @Autowired
    public OdontologoServiceTest(OdontologoService service) {
        this.service = service;
    }

    @Test
    public void listar() throws Exception {
        Odontologo odontologo= new Odontologo("RODOLFO","PEREZ","35289");
        odontologo=service.agregar(odontologo);
        Assertions.assertNotNull(odontologo);
        List<Odontologo> odontologos = service.listar();
        Assertions.assertNotNull(odontologos);
        Assertions.assertFalse(odontologos.isEmpty());
    }

    @Test
    public void guardar() throws Exception {
        Odontologo odontologo= new Odontologo("RODOLFO","PEREZ","65289");
        odontologo=service.agregar(odontologo);
        Assertions.assertNotNull(odontologo);
    }

    @Test
    public void eliminar() throws Exception {
        Odontologo odontologo= new Odontologo("RODOLFO","PEREZ","15289");
        odontologo=service.agregar(odontologo);
        Assertions.assertNotNull(odontologo);
        boolean eliminado=service.eliminar("15289");
        Assertions.assertTrue(eliminado);
    }

    @Test
    public void modificar() throws Exception {
        Odontologo odontologo=new Odontologo("RODOLFO","AICARDI","25926");
        odontologo=service.agregar(odontologo);

        String nuevoNombre="ALAN";
        odontologo.setNombre(nuevoNombre);
        odontologo=service.modificar(odontologo);

        Assertions.assertEquals(nuevoNombre,odontologo.getNombre());
    }
    @Test
    public void buscarPorId() throws Exception {
        Odontologo odontologo=new Odontologo("RODOLFO","AICARDI","25936");
        service.agregar(odontologo);
        odontologo=service.buscarPorId(odontologo.getMatricula());
        Assertions.assertNotNull(odontologo);
    }

}
