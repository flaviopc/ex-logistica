package com.log.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.log.api.model.EntregaModel;
import com.log.domain.model.Entrega;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {
    private ModelMapper modelMapper;

    public EntregaModel toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream().map(this::toModel).collect(Collectors.toList());
    }
}
