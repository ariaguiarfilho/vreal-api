package com.vreal.api.model.centro;

import com.vreal.domain.model.centro.enumeration.GrauSocio;
import com.vreal.domain.model.centro.enumeration.StatusSocio;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//exibe apenas propriedades que não estejam nulas
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SocioDTO {

    private Long id;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private GrauSocio grauSocio;

    @Enumerated(EnumType.STRING)
    private StatusSocio statusSocio;

    private boolean ativo;

    @NotBlank
    private String nomeUsual;

    private Long idNucleo;
    private String nomeNucleo;
    private String regiao;


    @NotBlank
    private String cpf;

    private String identificadorBoleto;

    private Integer identificadorReuni;

    private boolean gerarBoleto;


}
