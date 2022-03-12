package com.vreal.domain.repository.centro.socio;

import com.vreal.domain.model.centro.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio,Long>, SocioRepositoryQuery {
}
