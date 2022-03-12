package com.vreal.domain.model.seguranca.enumeration;

import lombok.Getter;

public enum NivelAcesso {

    NEGADO("Negado", 0),
    CONSULTA("Consulta", 1),
    CADASTRO("Cadastro", 2),
    EDITAR("Editar", 3),
    EXCLUIR("Excluir", 4),
    TUDO("Tudo", 5);

    @Getter
    private final String descricao;
    @Getter
    private final Integer indice;

    private NivelAcesso(String descricao, Integer indice) {
        this.descricao = descricao;
        this.indice = indice;
    }



    public static NivelAcesso getNivelPor(Integer indice) {
        for (NivelAcesso nv : NivelAcesso.values()) {
            if (nv.indice.equals(indice)) {
                return nv;
            }
        }
        return null;
    }
}
