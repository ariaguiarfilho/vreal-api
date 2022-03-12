package com.vreal.domain.service.centro;

import com.vreal.domain.model.centro.model.Regiao;
import com.vreal.domain.repository.centro.regiao.RegiaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class RegiaoService {

    private RegiaoRepository regiaoRepository;


    @Transactional
    public Regiao atualizar( Integer id,  Regiao regiao){
        Regiao regiaoSalva = regiaoRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(regiao,regiaoSalva,"id");
        return regiaoRepository.save(regiaoSalva);
    }
}
