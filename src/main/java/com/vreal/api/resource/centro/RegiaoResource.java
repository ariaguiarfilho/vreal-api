package com.vreal.api.resource.centro;

import com.vreal.api.mapper.centro.RegiaoMapper;
import com.vreal.api.model.centro.RegiaoDTO;
import com.vreal.api.model.centro.input.RegiaoInput;
import com.vreal.domain.model.centro.model.Regiao;
import com.vreal.domain.repository.centro.regiao.RegiaoRepository;
import com.vreal.domain.service.centro.RegiaoService;
import com.vreal.api.event.RecursoCriadoEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/regioes")
public class RegiaoResource {

    private RegiaoRepository regiaoRepository;
    private RegiaoService regiaoService;
    private ApplicationEventPublisher publisher;
    private RegiaoMapper regiaoMapper;


    @GetMapping("/consultar")
    public List<Regiao> consultar() {
        return regiaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Regiao> buscarPorId(@PathVariable Integer id) {
        return regiaoRepository.findById(id)
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RegiaoDTO> criar(@Valid @RequestBody RegiaoInput regiaoInput, HttpServletResponse response) {

        Regiao novaRegiao = regiaoMapper.toEntity(regiaoInput);
        Regiao regiaoSalva = regiaoRepository.save(novaRegiao);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, regiaoSalva.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(regiaoMapper.toDTO(regiaoSalva));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id) {
        this.regiaoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegiaoDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody RegiaoInput regiaoInput) {

        Regiao regiao = regiaoMapper.toEntity(regiaoInput);

        Regiao regiaoSalva = regiaoService.atualizar(id, regiao);
        return ResponseEntity.ok(regiaoMapper.toDTO(regiaoSalva));
    }


}
