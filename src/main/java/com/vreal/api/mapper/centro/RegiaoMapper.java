package com.vreal.api.mapper.centro;

import com.vreal.api.model.centro.RegiaoDTO;
import com.vreal.api.model.centro.input.RegiaoInput;
import com.vreal.domain.model.centro.model.Regiao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class RegiaoMapper {

    private ModelMapper modelMapper;

    public RegiaoDTO toDTO(Regiao regiao) {
        return modelMapper.map(regiao, RegiaoDTO.class);
    }

    public Regiao toEntity(RegiaoInput regiaoInput) {
        return modelMapper.map(regiaoInput, Regiao.class);
    }



}
