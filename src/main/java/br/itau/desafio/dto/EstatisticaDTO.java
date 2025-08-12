package br.itau.desafio.dto;

public record EstatisticaDTO(
        long count,
        double sum,
        double avg,
        double min,
        double max
) {}
