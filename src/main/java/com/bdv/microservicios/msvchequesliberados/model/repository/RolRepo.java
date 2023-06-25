package com.bdv.microservicios.msvchequesliberados.model.repository;


import com.bdv.microservicios.msvchequesliberados.model.entity.security.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepo extends JpaRepository<Rol,Long> {
}
