package com.trabajo.integrador.service.interfaces;

import java.util.List;

public interface ICrudService <T, ID>{
    List<T> listar();
    boolean eliminar(ID id) throws Exception;
    T agregar(T objeto) throws Exception;
    T modificar(T objeto) throws Exception;
    T buscarPorId(ID id) throws Exception;
}
