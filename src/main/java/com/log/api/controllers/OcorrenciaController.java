package com.log.api.controllers;

import javax.validation.Valid;

import com.log.api.assembler.OcorrenciaAssembler;
import com.log.api.model.OcorrenciaModel;
import com.log.api.model.input.OcorrenciaInput;
import com.log.domain.model.Ocorrencia;
import com.log.domain.service.OcorrenciaService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private OcorrenciaService ocorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId,
            @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrenciaRegistrada = ocorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }
}
