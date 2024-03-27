package com.trabajo.integrador;

import com.trabajo.integrador.entity.Odontologo;
import com.trabajo.integrador.entity.Paciente;
import com.trabajo.integrador.entity.Turno;
import com.trabajo.integrador.service.OdontologoService;
import com.trabajo.integrador.service.PacienteService;
import com.trabajo.integrador.service.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class TurnoServiceTest {
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    private TurnoService turnoService;

    @Autowired
    public TurnoServiceTest(OdontologoService odontologoService, PacienteService pacienteService, TurnoService turnoService){
        this.pacienteService = pacienteService;
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
    }

    @Test
    public void guardar() throws Exception {
        Odontologo odontologo= new Odontologo("RODOLFO","PEREZ","65589");
        Paciente paciente =new Paciente("CAMILO","PEREZ","AV 9","52596");
        LocalDateTime fecha= LocalDateTime.now();
        System.out.println(fecha.toString());
        this.odontologoService.agregar(odontologo);
        this.pacienteService.agregar(paciente);

        Turno fechaTurno= new Turno(fecha,odontologo,paciente );
        Turno turno=turnoService.agregar(fechaTurno);
        Assertions.assertNotNull(turno);
    }

    @Test
    public void listar() throws Exception {
        Odontologo odontologo= new Odontologo("RODOLFO","PEREZ","55589");
        Paciente paciente =new Paciente("CAMILO","PEREZ","AV 9","42596");
        LocalDateTime fecha= LocalDateTime.now();

        this.odontologoService.agregar(odontologo);
        this.pacienteService.agregar(paciente);

        Turno fechaTurno= new Turno(fecha,odontologo,paciente );
        turnoService.agregar(fechaTurno);

        List<Turno> turnos=turnoService.listar();
        Assertions.assertNotNull(turnos);
        Assertions.assertFalse(turnos.isEmpty());
    }

    @Test
    public void eliminar() throws Exception {
        Odontologo odontologo= new Odontologo("RODOLFO","PEREZ","45689");
        Paciente paciente =new Paciente("CAMILO","PEREZ","AV 9","32696");
        LocalDateTime fecha= LocalDateTime.now();

        this.odontologoService.agregar(odontologo);
        this.pacienteService.agregar(paciente);

        Turno fechaTurno= new Turno(fecha,odontologo,paciente );
        turnoService.agregar(fechaTurno);
        Assertions.assertTrue(turnoService.eliminar(fechaTurno.getId()));
    }

    @Test
    public void modificar() throws Exception {
        Odontologo odontologo= new Odontologo("RODOLFO","PEREZ","45289");
        Paciente paciente =new Paciente("CAMILO","PEREZ","AV 9","32896");
        LocalDateTime fecha= LocalDateTime.now();

        this.odontologoService.agregar(odontologo);
        this.pacienteService.agregar(paciente);

        Turno turno= new Turno(fecha,odontologo,paciente );
        turnoService.agregar(turno);

        fecha= LocalDateTime.now();
        turno.setFecha(fecha);
        turno=turnoService.modificar(turno);
        Assertions.assertEquals(fecha, turno.getFecha());
    }

    @Test
    public void buscarPorId() throws Exception {
        Odontologo odontologo= new Odontologo("RODOLFO","PEREZ","45209");
        Paciente paciente =new Paciente("CAMILO","PEREZ","AV 9","32806");
        LocalDateTime fecha= LocalDateTime.now();

        this.odontologoService.agregar(odontologo);
        this.pacienteService.agregar(paciente);

        Turno turno= new Turno(fecha,odontologo,paciente);
        turnoService.agregar(turno);
        turno=turnoService.buscarPorId(turno.getId());
        Assertions.assertNotNull(turno);
    }
}
