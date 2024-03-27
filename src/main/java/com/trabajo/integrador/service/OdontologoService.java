package com.trabajo.integrador.service;

import com.trabajo.integrador.entity.Odontologo;
import com.trabajo.integrador.exception.CustomErrorException;
import com.trabajo.integrador.exception.GlobalException;
import com.trabajo.integrador.repository.IOdontologoRepository;
import com.trabajo.integrador.service.interfaces.ICrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OdontologoService")
public class OdontologoService implements ICrudService<Odontologo,String> {

    private static final Logger LOGGER= Logger.getLogger(OdontologoService.class);

    private final IOdontologoRepository repository;

    @Autowired
    public OdontologoService(@Qualifier("OdontologoRep") IOdontologoRepository repository){
        this.repository=repository;
    }
    @Override
    public List<Odontologo> listar(){
        LOGGER.info("listando los odontologos");
        return repository.findAll();
    }
    @Override
    public boolean eliminar(String id) throws Exception{
        if(!this.repository.existsById(id)){
            throw new CustomErrorException("Imposible eliminar la informaciòn del odontologo", 409);
        }
        LOGGER.info("Eliminando odontologo con matricula: "+ id);
        this.repository.deleteById(id);
        return true;
    }
    @Override
     public Odontologo agregar(Odontologo objeto) throws Exception{
        if(this.repository.existsById(objeto.getMatricula())){
            throw new CustomErrorException("Ya existe un odontologo con el mismo número de matricula", 406);
        }
        LOGGER.info("Creando odontologo con matricula: " + objeto.getMatricula());
        return this.repository.save(objeto);
     }
    @Override
     public Odontologo modificar(Odontologo objeto) throws Exception{
        if(!this.repository.existsById(objeto.getMatricula())){
            throw new CustomErrorException("Imposible actualizar la informaciòn del odontologo indicado", 409);
        }
        LOGGER.info("Modificando odontologo con matricula: " + objeto.getMatricula());
        return this.repository.save(objeto);
     }

    @Override
    public Odontologo buscarPorId(String id) throws Exception{
        if(!this.repository.existsById(id)){
            throw new CustomErrorException("Odontologo no encontrado", 404);
        }
        LOGGER.info("Buscando el odontologo con matricula: " + id);
        return this.repository.findById(id).get();
    }
}
