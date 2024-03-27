package com.trabajo.integrador.repository;

import com.trabajo.integrador.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("OdontologoRep")
public interface IOdontologoRepository extends JpaRepository<Odontologo, String> {
}
