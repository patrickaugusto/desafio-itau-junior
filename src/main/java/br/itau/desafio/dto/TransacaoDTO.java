package br.itau.desafio.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.*;

public record TransacaoDTO(

    @PositiveOrZero(message = "O valor não pode ser negativo")
    @NotNull(message = "O valor é obrigatório")
    BigDecimal valor,

    @PastOrPresent(message = "A data não pode ser no futuro")
    @NotNull(message = "A data é obrigatória")
    OffsetDateTime dataHora

) {
    
}
