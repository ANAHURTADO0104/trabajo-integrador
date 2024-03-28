package com.trabajo.integrador.controller;

import com.trabajo.integrador.dto.TurnoDTO;
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
    public ResponseEntity<Turno> agendar (@RequestBody TurnoDTO turno) throws Exception {
        Paciente paciente = this.pacienteService.buscarPorId(turno.getPaciente());
        Odontologo odontologo = this.odontologoService.buscarPorId(turno.getOdontologo());
        Turno nuevoTurno = new Turno(turno.getFecha(), odontologo, paciente);
        if(paciente != null && odontologo!= null){
            return ResponseEntity.ok(this.turnoService.agregar(nuevoTurno));
        }
        throw new CustomErrorException("Odontologo o paciente no encontrado", 409);
    }

    @GetMapping
    public ResponseEntity<Object> listar (@RequestParam(required = false) Long id) throws Exception{
        if(id != null && id > 0){
            return ResponseEntity.ok(this.turnoService.buscarPorId(id));
        }
        return ResponseEntity.ok(this.turnoService.listar());
    }

    @PutMapping
    public ResponseEntity<Turno> editar(@RequestBody Turno turno) throws Exception{
        return ResponseEntity.ok(this.turnoService.modificar(turno));
    }

    @DeleteMapping Boolean eliminar(@RequestParam Long id) throws Exception{
        return this.turnoService.eliminar(id);
    }
}
