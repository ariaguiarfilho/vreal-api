package com.vreal.api.model.centro.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class RegiaoInput {
    private Integer id;
    private Integer idReuni;

    private String descricao;

    private String nomeCentral;

    private String telefoneCentral;

    private String celularCentral;

    @Email
    private String emailCentral;
}
