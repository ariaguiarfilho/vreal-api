package com.vreal.domain.repository.centro.socio;

import com.vreal.domain.model.centro.model.Socio;
import com.vreal.api.model.centro.SocioDTO;
import com.vreal.domain.repository.centro.filter.SocioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SocioRepositoryQuery {

    public Page<Socio> filtrar(SocioFilter socioFilter, Pageable pageable);
    public Page<SocioDTO> consultar(SocioFilter socioFilter, Pageable pageable);

}
