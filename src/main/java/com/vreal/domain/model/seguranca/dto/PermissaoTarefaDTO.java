package com.vreal.domain.model.seguranca.dto;

import com.vreal.domain.model.seguranca.enumeration.NivelAcesso;
import com.vreal.domain.model.seguranca.enumeration.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoTarefaDTO {

    public Tarefa tarefa;
    public NivelAcesso nivelAcesso;
}
