package com.vreal.domain.service.centro;

import com.vreal.domain.model.centro.model.Nucleo;
import com.vreal.domain.repository.centro.nucleo.NucleoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class NucleoService {

    private NucleoRepository nucleoRepository;

    //anotação para controlar a transação
    //caso algo dê errado é feito o rollback n
    @Transactional
    public Nucleo atualizar(Long id, Nucleo nucleo){

        Nucleo nucleoSalvo = this.nucleoRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(nucleo,nucleoSalvo,"id");
        return this.nucleoRepository.save(nucleoSalvo);
    }

    public void atualizarPropriedadeAtivarSistema(Long id, Boolean ativo){
        Nucleo nucleo = this.nucleoRepository.findById(id).get();
        nucleo.setAtivarSistema(ativo);
        this.nucleoRepository.save(nucleo);
    }
}
