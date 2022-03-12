package com.vreal.api.resource.centro;

import com.vreal.domain.model.centro.model.Nucleo;
import com.vreal.domain.repository.centro.nucleo.NucleoRepository;
import com.vreal.domain.repository.centro.filter.NucleoFilter;
import com.vreal.domain.service.centro.NucleoService;
import com.vreal.api.event.RecursoCriadoEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/nucleos")
public class NucleoResource {

    private NucleoRepository nucleoRepository;
    private NucleoService nucleoService;
    private ApplicationEventPublisher publisher;

    @GetMapping("/todos")
    @PreAuthorize("hasAuthority('ROLE_MANTER_NUCLEO') and hasAuthority('SCOPE_read')")
    public List<Nucleo> consultar() {
        return nucleoRepository.findAll();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_MANTER_NUCLEO') and hasAuthority('SCOPE_read')")
    public Page<Nucleo> pesquisar(NucleoFilter nucleoFilter, Pageable pageable) {
        return nucleoRepository.filtrar(nucleoFilter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nucleo> buscarPorId(@PathVariable Long id) {
        return nucleoRepository.findById(id)
//                .map(r -> ResponseEntity.ok(r))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Nucleo> criar(@Valid @RequestBody Nucleo nucleo, HttpServletResponse response) {
        Nucleo nucleoSalvo = nucleoRepository.save(nucleo);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, nucleoSalvo.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(nucleoSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        this.nucleoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nucleo> atualizar(@PathVariable Long id, @Valid @RequestBody Nucleo nucleo) {
        Nucleo nucleoSalvo = this.nucleoService.atualizar(id, nucleo);
        return ResponseEntity.ok(nucleoSalvo);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivarSistema(@PathVariable Long id, @Valid @RequestBody Boolean ativo) {
        this.nucleoService.atualizarPropriedadeAtivarSistema(id, ativo);
    }
}
