package com.vreal.domain.repository.centro.nucleo;

import com.vreal.domain.model.centro.model.Nucleo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NucleoRepository extends JpaRepository<Nucleo,Long>, NucleoRepositoryQuery {
    //queryMethod - nome das querys spring data jpa
}
