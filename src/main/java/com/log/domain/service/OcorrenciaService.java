package com.log.domain.service;

import com.log.domain.model.Entrega;
import com.log.domain.model.Ocorrencia;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);
        return entrega.adicionaOcorrencia(descricao);
    }
}
