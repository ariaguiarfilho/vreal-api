package com.vreal.domain.repository.seguranca;

import com.vreal.domain.model.seguranca.enumeration.Perfil;
import com.vreal.domain.model.seguranca.enumeration.Tarefa;
import com.vreal.domain.model.seguranca.model.PermissaoPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissaoPerfilRepository extends JpaRepository<PermissaoPerfil,Long> {

    public List<PermissaoPerfil> findByPerfil(Perfil perfil);
    public Optional<PermissaoPerfil> findByPerfilAndTarefa(Perfil perfil, Tarefa tarefa);

}
