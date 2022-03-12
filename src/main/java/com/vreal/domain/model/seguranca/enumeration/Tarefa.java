package com.vreal.domain.model.seguranca.enumeration;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public enum Tarefa {
    /*
       identificador =  cod identificador de permissao perfil
       Digito      PerfilProfissional
       1 - SECRETARIA,
       2 - TESOURARIA,
       3 - PATRIMONIO,
       4 - PRESIDENCIA,
       5 - SOCIO,
       6 - ADMINISTRADOR_SISTEMA,

   -------NIVEL ACESSO
       0 - NEGADO,
       1 - CONSULTA
       2 - CADASTRO
       3 - EDITAR
       4 - EXCLUIR
       5 - TUDO
    */

    MANTER_PERFIL("Manter Perfil", "000005", Modulo.SEGURANCA),
    MANTER_USUARIO("Manter Usuário", "040005", Modulo.SEGURANCA),
    MANTER_NUCLEO("Manter Núcleo", "000005", Modulo.CENTRO),
    MANTER_REGIAO("Manter Região", "000005", Modulo.CENTRO),
    MANTER_SOCIO("Manter Sócio", "441105", Modulo.CENTRO),
    ;

    @Getter
    private final String nome;
    @Getter
    private final String identificador;
    @Getter
    private final Modulo modulo;

    Tarefa(String nome, String identificador, Modulo modulo) {
        this.nome = nome;
        this.identificador = identificador;
        this.modulo = modulo;
    }

    public String getDescricaoRole() {
        return "ROLE_".concat(toString());
    }

    public Integer identificardor(Integer ini) {
        switch (ini) {
            case 1:
                return Integer.parseInt(this.getIdentificador().substring(0, 1));
            case 2:
                return Integer.parseInt(this.getIdentificador().substring(1, 2));
            case 3:
                return Integer.parseInt(this.getIdentificador().substring(2, 3));
            case 4:
                return Integer.parseInt(this.getIdentificador().substring(3, 4));
            case 5:
                return Integer.parseInt(this.getIdentificador().substring(4, 5));
            case 6:
                return Integer.parseInt(this.getIdentificador().substring(5, 6));


            default:
                return null;
        }
    }

    public static List<Tarefa> porModulo(Modulo modulo) {
        List<Tarefa> tarefas = new ArrayList<>();
        for (Tarefa t : Tarefa.values()) {
            if (t.modulo.equals(modulo)) {
                tarefas.add(t);
            }
        }
        return tarefas;
    }
}
