package com.vreal.domain.repository.centro.nucleo;

import com.vreal.domain.model.centro.model.Nucleo;
import com.vreal.domain.repository.centro.filter.NucleoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NucleoRepositoryQuery {

    public Page<Nucleo> filtrar(NucleoFilter nucleoFilter, Pageable pageable);
}
