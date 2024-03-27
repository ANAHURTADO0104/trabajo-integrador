package com.trabajo.integrador.repository;

import com.trabajo.integrador.entity.Odontologo;
import com.trabajo.integrador.entity.Paciente;
import com.trabajo.integrador.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("TurnoRep")
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

    @Query("SELECT COUNT(t) > 0 FROM Turno t WHERE t.fecha = ?1 AND t.odontologo = ?2")
    boolean existePorFechaYOdontologo(LocalDateTime fecha, Odontologo odontologo);

    @Query("SELECT COUNT(t) > 0 FROM Turno t WHERE t.fecha = ?1 AND t.paciente = ?2")
    boolean existePorFechaYPaciente(LocalDateTime fecha, Paciente paciente);
}
