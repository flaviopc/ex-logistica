package com.log.domain.service;

import com.log.domain.exception.NegocioException;
import com.log.domain.model.Entrega;
import com.log.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
    EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new NegocioException("Entrega não encontrada!"));
        return entrega;
    }
}
