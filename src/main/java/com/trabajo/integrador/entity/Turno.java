package com.trabajo.integrador.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="turno", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fecha", "odontologo_matricula"}),
        @UniqueConstraint(columnNames = {"fecha", "paciente_dni"})
})
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    @ManyToOne
    private Odontologo odontologo;
    @ManyToOne
    private Paciente paciente;

    public Turno (LocalDateTime fecha, Odontologo odontologo, Paciente paciente){
        this.paciente=paciente;
        this.odontologo=odontologo;
        this.fecha=fecha;
    }
}
