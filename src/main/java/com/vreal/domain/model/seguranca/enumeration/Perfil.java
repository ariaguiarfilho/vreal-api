package com.vreal.domain.model.seguranca.enumeration;

public enum Perfil {



    SECRETARIA("Secretaria"),
    TESOURARIA("Tesouraria"),
    PATRIMONIO("Patrimônio"),
    PRESIDENCIA("Presidência"),
    SOCIO("Sócio"),
    ADMINISTRADOR_SISTEMA("Administrador");

    private final String descricao;

    private Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
