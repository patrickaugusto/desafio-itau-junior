package br.itau.desafio.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.itau.desafio.dto.TransacaoDTO;
import br.itau.desafio.exception.BadRequestException;
import br.itau.desafio.exception.UnprocessableEntityException;
import br.itau.desafio.model.Transacao;
import br.itau.desafio.repository.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    
    public TransacaoDTO adicionarTransacao(TransacaoDTO transacaoDto) throws Exception{
        if (transacaoDto.valor() == null || transacaoDto.valor().compareTo(BigDecimal.ZERO) <= 0) {
    throw new UnprocessableEntityException("Valor inválido para transação");
}


        if (transacaoDto.dataHora().isAfter(OffsetDateTime.now())) {
            throw new BadRequestException("Data/hora não pode ser no futuro");
        }

        Transacao transacao = new Transacao(transacaoDto.valor(), transacaoDto.dataHora());
        transacao = transacaoRepository.adicionar(transacao);
        return new TransacaoDTO(transacao.getValor(),transacao.getDataHora());

    }
}
