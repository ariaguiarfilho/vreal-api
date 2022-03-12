package com.vreal.domain.service.seguranca;

import com.vreal.domain.model.seguranca.dto.PermissaoTarefaDTO;
import com.vreal.domain.model.seguranca.model.PermissaoPerfil;
import com.vreal.domain.model.seguranca.model.PermissaoUsuario;
import com.vreal.domain.model.seguranca.model.Usuario;
import com.vreal.domain.repository.seguranca.PermissaoPerfilRepository;
import com.vreal.domain.repository.seguranca.PermissaoUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioService {


    private PermissaoUsuarioRepository permissaoUsuarioRepository;
    private PermissaoPerfilRepository permissaoPerfilRepository;



    public List<PermissaoTarefaDTO> listaDePermissoes(Usuario usuario){
        List<PermissaoPerfil> listaDePermissaoPerfil = permissaoPerfilRepository.findByPerfil(usuario.getPerfil());
        List<PermissaoUsuario> listaDePermissaoUsuarios = permissaoUsuarioRepository.findByUsuario(usuario);

        List<PermissaoTarefaDTO> lista = new ArrayList<>();

        listaDePermissaoPerfil.forEach(permissaoPerfil -> {

            PermissaoTarefaDTO dto = PermissaoTarefaDTO.builder()
                    .tarefa(permissaoPerfil.getTarefa())
                    .nivelAcesso(permissaoPerfil.getNivelAcesso())
                    .build();

            for (PermissaoUsuario permissaoUsuario : listaDePermissaoUsuarios ) {
                if (dto.tarefa.equals(permissaoUsuario.getTarefa())){
                    dto.nivelAcesso = permissaoUsuario.getNivelAcesso();
                    break;
                }
            }
            lista.add(dto);

        });
        return lista;
    }
}
