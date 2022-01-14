package com.log.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModel {
    private Long id;
    private String nomeCliente;
    private DestinatarioModel destinatario;
    private BigDecimal taxa;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
