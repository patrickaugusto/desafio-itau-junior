package br.itau.desafio.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.itau.desafio.dto.EstatisticaDTO;
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

    public List<Transacao> listarTransacoes() {
        var transacoes = transacaoRepository.listar();
        return transacoes;
    }

    public void deletarTransacoes(){
        transacaoRepository.deletar();;
    }

    public EstatisticaDTO estatisticasUltimosSegundos(Integer segundos) {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime limite = agora.minusSeconds(segundos);

        var stats = listarTransacoes().stream()
            .filter(t -> t.getDataHora().isAfter(limite))
            .mapToDouble(t -> t.getValor().doubleValue())
            .summaryStatistics();
        
        if (stats.getCount() == 0) {
            return new EstatisticaDTO(0, 0.0, 0.0, 0.0, 0.0);
        }

        return new EstatisticaDTO(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }
}
