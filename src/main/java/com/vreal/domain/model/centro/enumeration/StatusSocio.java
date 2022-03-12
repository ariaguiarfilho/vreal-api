package com.vreal.domain.model.centro.enumeration;

public enum StatusSocio {
    ATIVO("Ativo",1),
    AFASTOU_SE("Afastou-se",2),
    AFASTADO("Afastado",6),
    SUSPENSO("Suspenso",5),
    TRANSFERIDO("Transferido",99),
    FALECIDO("Falecido",8),
    AFASTADO_AMBITO("Afastado do Âmbito",7),
    LICENCIADO_CONTRIBUINTE("Licenciado Contribuinte",3),
    LICENCIADO("Licenciado",4);

    private final String descricao;
    private final Integer referenciaReuni;

    private StatusSocio(String descricao, Integer referenciaReuni) {
        this.descricao = descricao;
        this.referenciaReuni = referenciaReuni;
    }

    public static StatusSocio pegarPorReferencia(Integer ref) {
        switch (ref) {
            case 1:
                return ATIVO;
            case 2:
                return AFASTOU_SE;
            case 3:
                return LICENCIADO_CONTRIBUINTE;
            case 4:
                return LICENCIADO;
            case 5:
                return SUSPENSO;
            case 6:
                return AFASTADO;
            case 7:
                return AFASTADO_AMBITO;
            case 8:
                return FALECIDO;
        }
        return null;
    }


    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
