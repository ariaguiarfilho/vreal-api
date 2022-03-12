package com.vreal.api.mapper.centro;

import com.vreal.api.model.centro.SocioDTO;
import com.vreal.domain.model.centro.model.Socio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class SocioMapper {

    private ModelMapper modelMapper;

    public SocioDTO toDTO(Socio socio){
        return modelMapper.map(socio,SocioDTO.class);
    }

    public List<SocioDTO> toListDTO(List<Socio> socios){

        return socios.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

}
