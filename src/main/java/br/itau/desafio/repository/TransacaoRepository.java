package br.itau.desafio.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.itau.desafio.model.Transacao;

@Repository
public class TransacaoRepository {
    private final Map<Long, Transacao> transacoes = new HashMap<>();

    private long sequencia = 1;

    public Transacao adicionar(Transacao transacao){
        transacao.setId(sequencia++);
        transacoes.put(transacao.getId(), transacao);
        return transacao;
    }

    public List<Transacao> listar() {
        return new ArrayList<>(transacoes.values());
    }

    public void deletar(){
        transacoes.clear(); 
    }

}
