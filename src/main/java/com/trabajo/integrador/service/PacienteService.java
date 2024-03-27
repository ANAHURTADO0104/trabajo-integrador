package com.trabajo.integrador.service;

import com.trabajo.integrador.entity.Paciente;
import com.trabajo.integrador.exception.CustomErrorException;
import com.trabajo.integrador.repository.IPacienteRepository;
import com.trabajo.integrador.service.interfaces.ICrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PacienteService")
public class PacienteService implements ICrudService<Paciente, String> {

    private static final Logger LOGGER= Logger.getLogger(PacienteService.class);

    private final IPacienteRepository repository;
    @Autowired
    public PacienteService(@Qualifier("PacienteRep") IPacienteRepository repository){
        this.repository=repository;
    }
    @Override
    public List<Paciente> listar (){
        LOGGER.info("listando los pacientes");
        return this.repository.findAll();
    }
    @Override
    public boolean eliminar(String dni) throws Exception{
        if(!this.repository.existsById(dni)){
            throw new CustomErrorException("Imposible eliminar la informaciòn del paciente", 409);
        }
        LOGGER.info("Eliminando paciente con dni: "+ dni);
        this.repository.deleteById(dni);
        return true;
    }
    @Override
    public Paciente agregar (Paciente pa) throws Exception {
        if(this.repository.existsById(pa.getDni())){
            throw new CustomErrorException("Ya existe un paciente con el mismo DNI", 406);
        }
        LOGGER.info("Creando paciente con dni: " + pa.getDni());
        return this.repository.save(pa);
    }
    @Override
    public Paciente modificar (Paciente pa) throws Exception{
        if(!this.repository.existsById(pa.getDni())){
            throw new CustomErrorException("Imposible actualizar la informaciòn del paciente indicado", 409);
        }
        LOGGER.info("Modificando paciente con dni: " + pa.getDni());
        return this.repository.save(pa);
    }

    @Override
    public Paciente buscarPorId(String dni) throws Exception {
        if(!this.repository.existsById(dni)){
            throw new CustomErrorException("Paciente no encontrado", 404);
        }
        LOGGER.info("Buscando el paciente con dni: " + dni);
        return this.repository.findById(dni).get();
    }
}
