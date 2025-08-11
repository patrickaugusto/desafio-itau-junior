package br.itau.desafio.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Transacao {
    private Long id;
    private BigDecimal valor;
    private OffsetDateTime dataHora;

    public Transacao(BigDecimal valor, OffsetDateTime dataHora){
        this.valor = valor;
        this.dataHora = dataHora;
    }
}
