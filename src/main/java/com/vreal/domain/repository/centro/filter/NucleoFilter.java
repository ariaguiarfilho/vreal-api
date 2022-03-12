package com.vreal.domain.repository.centro.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NucleoFilter {

    private String nome;
    private Integer idRegiao;

    //caso tenha filtro de data
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate data;
}
