package com.trabajo.integrador.service;

import com.trabajo.integrador.entity.Turno;
import com.trabajo.integrador.exception.CustomErrorException;
import com.trabajo.integrador.repository.ITurnoRepository;
import com.trabajo.integrador.service.interfaces.ICrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TurnoService")
public class TurnoService implements ICrudService<Turno, Long> {

    private static final Logger LOGGER= Logger.getLogger(TurnoService.class);

    private final ITurnoRepository repository;
 @Autowired
public TurnoService (@Qualifier("TurnoRep") ITurnoRepository repository){
    this.repository=repository;
}
    @Override
    public List<Turno> listar() {
        LOGGER.info("listando los turnos");
        return this.repository.findAll();
    }

    @Override
    public boolean eliminar(Long id) throws Exception{
        if(!this.repository.existsById(id)){
            throw new CustomErrorException("Imposible eliminar la informaciòn del turno", 409);
        }
        LOGGER.info("Eliminando turno con id: "+ id);
        this.repository.deleteById(id);
        return true;
    }

    @Override
    public Turno agregar(Turno objeto) throws Exception{
        if(this.repository.existePorFechaYOdontologo(objeto.getFecha(), objeto.getOdontologo())){
            throw new CustomErrorException("El odontologo ya tiene un turno agendado en la misma fecha y hora", 406);
        }
        if(this.repository.existePorFechaYPaciente(objeto.getFecha(), objeto.getPaciente())){
            throw new CustomErrorException("El paciente ya tiene un turno agendado en la misma fecha y hora", 406);
        }
        LOGGER.info("Creando turno con id: " + objeto.getId());
        return this.repository.save(objeto);
    }

    @Override
    public Turno modificar(Turno objeto)throws Exception {
        if(!this.repository.existsById(objeto.getId())){
            throw new CustomErrorException("Imposible actualizar la informaciòn del turno indicado", 409);
        }
        LOGGER.info("Modificando turno con id: " + objeto.getId());
        return this.repository.save(objeto);
    }

    @Override
    public Turno buscarPorId(Long id) throws Exception{
        if(!this.repository.existsById(id)){
            throw new CustomErrorException("Turno no encontrado", 404);
        }
        LOGGER.info("Buscando el turno con id: " + id);
        return this.repository.findById(id).get();
    }
}
