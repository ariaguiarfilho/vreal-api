package com.vreal.domain.repository.seguranca;

import com.vreal.domain.model.seguranca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Optional<Usuario> findByLogin(String login);
}
