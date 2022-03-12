package com.vreal.domain.model.seguranca.dto;

import com.vreal.domain.model.seguranca.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistemaDTO extends User {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    public UsuarioSistemaDTO(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getLogin(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
