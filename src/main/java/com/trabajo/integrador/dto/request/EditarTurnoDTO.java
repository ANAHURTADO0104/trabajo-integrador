package com.trabajo.integrador.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class EditarTurnoDTO {
    private Long id;
    private LocalDateTime fecha;
    private String odontologo;
    private String paciente;
}
