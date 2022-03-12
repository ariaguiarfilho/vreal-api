package com.vreal.api.model.centro;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RegiaoDTO {

    private Integer id;

    private Integer idReuni;

    private String descricao;

    private String nomeCentral;

    private String telefoneCentral;

    private String celularCentral;

    @Email
    private String emailCentral;
}
