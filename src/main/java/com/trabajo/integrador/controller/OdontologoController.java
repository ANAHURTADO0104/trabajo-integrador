package com.trabajo.integrador.controller;

import com.trabajo.integrador.entity.Odontologo;
import com.trabajo.integrador.service.interfaces.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private final ICrudService<Odontologo,String> service;
    @Autowired
    public OdontologoController(ICrudService<Odontologo, String> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> listar (@RequestParam(required = false) String matricula) throws Exception {
        if(matricula != null && !matricula.trim().isEmpty()){
            return ResponseEntity.ok(this.service.buscarPorId(matricula));
        }
        return ResponseEntity.ok(this.service.listar());
    }

    @PutMapping
    public ResponseEntity<Odontologo> editar(@RequestBody Odontologo odontologo) throws Exception{
        return ResponseEntity.ok(this.service.modificar(odontologo));
    }

    @PostMapping
    public ResponseEntity<Odontologo> agregar(@RequestBody Odontologo odontologo)throws Exception {
        return ResponseEntity.ok(this.service.agregar(odontologo));
    }

    @DeleteMapping boolean eliminar(@RequestParam String matricula) throws Exception{
        return this.service.eliminar(matricula);
    }
}
