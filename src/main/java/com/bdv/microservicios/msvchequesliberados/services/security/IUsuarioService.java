package com.bdv.microservicios.msvchequesliberados.services.security;


import com.bdv.microservicios.msvchequesliberados.model.entity.security.Usuario;

public interface IUsuarioService {
    Usuario salvarUsuario(Usuario usuario);

    Usuario consultarUsuario(String nombreUsuario);

    void eliminarUsuario(Long idUsuario);

    Usuario modificarusuario(Usuario usuario);

}
