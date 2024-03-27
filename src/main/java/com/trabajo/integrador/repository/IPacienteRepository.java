package com.trabajo.integrador.repository;

import com.trabajo.integrador.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PacienteRep")
public interface IPacienteRepository extends JpaRepository<Paciente, String> {
}
