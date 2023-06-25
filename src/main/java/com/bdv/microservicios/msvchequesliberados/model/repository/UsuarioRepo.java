package com.bdv.microservicios.msvchequesliberados.model.repository;


import com.bdv.microservicios.msvchequesliberados.model.entity.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario,Long> {

    Usuario findUsuarioByUsername(String username);


}
