package com.trabajo.integrador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Paciente {
    private  String nombre;
    private String apellido;
    private String domicilio;
    @Id
    private String dni;
    private LocalDateTime fechaAlta;

    public Paciente(String nombre, String apellido, String domicilio, String dni){
        this.nombre=nombre;
        this.apellido=apellido;
        this.domicilio=domicilio;
        this.dni=dni;

    }

    @Override
    public String toString() {
        return " NOMBRE: " + this.nombre + " APELLIDO: " + this.apellido + " DOMICILIO: "+ this.domicilio + " DNI: " + this.dni + " FECHA_ALTA: " + this.fechaAlta;
    }
}
