package com.vreal.api.resource.centro;

import com.vreal.api.mapper.centro.SocioMapper;
import com.vreal.domain.model.centro.model.Socio;
import com.vreal.api.model.centro.SocioDTO;
import com.vreal.domain.repository.centro.filter.SocioFilter;
import com.vreal.domain.repository.centro.socio.SocioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/socios")
public class SocioResource {

    private SocioRepository socioRepository;
    private SocioMapper socioMapper;

    @GetMapping(params = "resumo")
    public Page<SocioDTO> consultar(SocioFilter socioFilter, Pageable pageable) {
        return socioRepository.consultar(socioFilter,pageable);
    }

    @GetMapping()
    public Page<Socio> pesquisar(SocioFilter socioFilter, Pageable pageable) {
        return socioRepository.filtrar(socioFilter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocioDTO> buscarPorId(@PathVariable Long id) {
        return socioRepository.findById(id)
                .map(socio -> ResponseEntity.ok(socioMapper.toDTO(socio)))
                .orElse(ResponseEntity.notFound().build());

    }
}
