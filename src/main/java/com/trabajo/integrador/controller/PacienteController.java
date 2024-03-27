package com.trabajo.integrador.controller;

import com.trabajo.integrador.entity.Paciente;
import com.trabajo.integrador.service.interfaces.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final ICrudService<Paciente,String> service;
    @Autowired
    public PacienteController(@Qualifier("PacienteService") ICrudService<Paciente, String> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> listar (@RequestParam(required = false) String dni) throws Exception {
        if(dni != null && !dni.trim().isEmpty()){
            return ResponseEntity.ok(this.service.buscarPorId(dni));
        }
        return ResponseEntity.ok(this.service.listar());

    }

    @PutMapping
    public ResponseEntity<Paciente> editar(@RequestBody Paciente paciente) throws Exception{
        return ResponseEntity.ok(this.service.modificar(paciente));
    }

    @PostMapping
    public ResponseEntity<Paciente> agregar(@RequestBody Paciente paciente) throws Exception{
        return ResponseEntity.ok(this.service.agregar(paciente));
    }

    @DeleteMapping boolean eliminar(@RequestParam(required = true) String dni) throws Exception{
        return this.service.eliminar(dni);
    }
}
