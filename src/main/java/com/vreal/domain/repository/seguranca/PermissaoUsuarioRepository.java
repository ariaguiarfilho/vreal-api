package com.vreal.domain.repository.seguranca;

import com.vreal.domain.model.seguranca.model.PermissaoUsuario;
import com.vreal.domain.model.seguranca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario,Long> {

    public List<PermissaoUsuario> findByUsuario(Usuario usuario);
}
