package com.vreal.domain.repository.centro.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocioFilter {

    private String nome;
    private String cpf;
    private Long idNucleo;

    //caso tenha filtro de data
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate data;
}
