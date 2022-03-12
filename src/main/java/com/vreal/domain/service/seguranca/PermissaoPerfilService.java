package com.vreal.domain.service.seguranca;

import com.vreal.domain.model.seguranca.enumeration.NivelAcesso;
import com.vreal.domain.model.seguranca.enumeration.Perfil;
import com.vreal.domain.model.seguranca.enumeration.Tarefa;
import com.vreal.domain.model.seguranca.model.PermissaoPerfil;
import com.vreal.domain.repository.seguranca.PermissaoPerfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PermissaoPerfilService {

    private PermissaoPerfilRepository permissaoPerfilRepository;



    //anotação para controlar a transação
    //caso algo dê errado é feito o rollback n
    @Transactional
    public void carregarPermissoesDoPerfil() {
        incluirPermissaoDoPerfil();
    }


    private void incluirPermissaoDoPerfil() {
        for (Perfil pp : Perfil.values()) {
            switch (pp) {
                case SECRETARIA:
                    addPermissao(pp, 1);
                    break;
                case TESOURARIA:
                    addPermissao(pp, 2);
                    break;
                case PATRIMONIO:
                    addPermissao(pp, 3);
                    break;
                case PRESIDENCIA:
                    addPermissao(pp, 4);
                    break;
                case SOCIO:
                    addPermissao(pp, 5);
                    break;
                case ADMINISTRADOR_SISTEMA:
                    addPermissao(pp, 6);
                    break;


            }
        }
    }

    private void addPermissao(Perfil pp, Integer indiceNivelTarefa) {
        System.out.println("======================================================================");
        System.out.println("              Incluindo Tarefas -- " + pp.getDescricao());
        System.out.println("======================================================================");

        for (Tarefa tf : Tarefa.values()) {

            NivelAcesso nv = NivelAcesso.getNivelPor(tf.identificardor(indiceNivelTarefa));
            Optional<PermissaoPerfil> ppp = permissaoPerfilRepository.findByPerfilAndTarefa(pp, tf);
            PermissaoPerfil permissaoPerfil = null;
            if (ppp.isPresent()) {
                 permissaoPerfil = ppp.orElseThrow();

                if (permissaoPerfil == null && !nv.equals(NivelAcesso.NEGADO)) {
                    permissaoPerfil = new PermissaoPerfil(pp, nv, tf);
                    permissaoPerfilRepository.save(permissaoPerfil);

                } else if (permissaoPerfil != null && !nv.equals(permissaoPerfil.getNivelAcesso())) {
                    permissaoPerfil.setNivelAcesso(nv);
                    permissaoPerfilRepository.save(permissaoPerfil);
                }
            }else {
                if (permissaoPerfil == null && !nv.equals(NivelAcesso.NEGADO)) {
                    permissaoPerfil = new PermissaoPerfil(pp, nv, tf);
                    permissaoPerfilRepository.save(permissaoPerfil);

                }
            }
        }
    }
}
