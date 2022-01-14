package com.log.api.controllers;

import java.util.List;

import javax.validation.Valid;

import com.log.api.assembler.EntregaAssembler;
import com.log.api.model.EntregaModel;
import com.log.domain.model.Entrega;
import com.log.domain.repository.EntregaRepository;
import com.log.domain.service.SolicitacaoEntregaService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaRepository entregaRepository;
    private EntregaAssembler entregaAssembler;

    @GetMapping
    public List<EntregaModel> listar() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> {
                    EntregaModel entregaModel = entregaAssembler.toModel(entrega);
                    return ResponseEntity.ok(entregaModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody Entrega entrega) {
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(entrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }
}
