package com.trabajo.integrador.controller;

import com.trabajo.integrador.dto.CrearTurnoDTO;
import com.trabajo.integrador.dto.EditarTurnoDTO;
import com.trabajo.integrador.entity.Odontologo;
import com.trabajo.integrador.entity.Paciente;
import com.trabajo.integrador.entity.Turno;
import com.trabajo.integrador.exception.CustomErrorException;
import com.trabajo.integrador.service.interfaces.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/turno")
public class TurnoController {
    private final ICrudService<Turno, Long> turnoService;
    private final ICrudService<Paciente, String> pacienteService;
    private final ICrudService<Odontologo, String> odontologoService;

    @Autowired
    public TurnoController(
            @Qualifier("TurnoService") ICrudService<Turno, Long> turnoService,
            @Qualifier("OdontologoService") ICrudService<Odontologo, String> odontologoService,
            @Qualifier("PacienteService") ICrudService<Paciente, String> pacienteService) {
    this.turnoService = turnoService;
    this.pacienteService = pacienteService;
    this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Turno> agendar (@RequestBody CrearTurnoDTO turno) throws Exception {
        Odontologo odontologo = this.odontologoService.buscarPorId(turno.getOdontologo());
        Paciente paciente = this.pacienteService.buscarPorId(turno.getPaciente());
        Turno nuevoTurno = new Turno(turno.getFecha(), odontologo, paciente);
        return ResponseEntity.ok(this.turnoService.agregar(nuevoTurno));
    }

    @GetMapping
    public ResponseEntity<Object> listar (@RequestParam(required = false) Long id) throws Exception{
        if(id != null && id > 0){
            return ResponseEntity.ok(this.turnoService.buscarPorId(id));
        }
        return ResponseEntity.ok(this.turnoService.listar());
    }

    @PutMapping
    public ResponseEntity<Turno> editar(@RequestBody EditarTurnoDTO turno) throws Exception{
        Odontologo odontologo = this.odontologoService.buscarPorId(turno.getOdontologo());
        Paciente paciente = this.pacienteService.buscarPorId(turno.getPaciente());
        Turno editarTurno = turnoService.buscarPorId(turno.getId());
        editarTurno.setFecha(turno.getFecha());
        editarTurno.setOdontologo(odontologo);
        editarTurno.setPaciente(paciente);
        return ResponseEntity.ok(this.turnoService.modificar(editarTurno));
    }

    @DeleteMapping Boolean eliminar(@RequestParam Long id) throws Exception{
        return this.turnoService.eliminar(id);
    }
}
