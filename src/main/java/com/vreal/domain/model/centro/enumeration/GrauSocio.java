package com.vreal.domain.model.centro.enumeration;

import lombok.Getter;

@Getter
public enum GrauSocio {
    SOCIO("Quadro de Sócio","QS",4),
    INSTRUTIVA("Corpo Instrutivo","CI",3),
    CONSELHO("Corpo do Conselho","CDC",2),
    MESTRE("Quadro de Mestre","QM",1),
    REPRESENTANTE("Mestre Representante","MR",5),
    ASSISTENTE_CENTRAL("Mestre Assistênte Central","MAC",6),
    CENTRAL("Mestre Central","MC",7),
    ASSISTENTE_GERAL("Mestre Assistênte Geral","MAG",8),
    CONSELHO_RECORDACAO("Mestre do Conselho da Recordação","MCR",10),
    GERAL_REPRESENTANTE("Mestre Geral Representante","MGR",9);

    private final String descricao;
    private final String abreviacao;
    private final Integer referenciaReuni;

    private GrauSocio(String descricao, String abreviacao, Integer referenciaReuni) {
        this.descricao = descricao;
        this.abreviacao = abreviacao;
        this.referenciaReuni = referenciaReuni;
    }

    public static GrauSocio pegarPorReferencia(Integer ref) {
        switch (ref) {
            case 1:
                return MESTRE;
            case 2:
                return CONSELHO;
            case 3:
                return INSTRUTIVA;
            case 4:
                return SOCIO;
            case 6:
                return REPRESENTANTE;
            case 7:
                return CENTRAL;
            case 8:
                return ASSISTENTE_GERAL;
            case 9:
                return GERAL_REPRESENTANTE;
            case 10:
                return CONSELHO_RECORDACAO;
        }
        return null;
    }
}
