package br.itau.desafio.controller;

import jakarta.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.itau.desafio.dto.EstatisticaDTO;
import br.itau.desafio.dto.TransacaoDTO;
import br.itau.desafio.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> adicionarTransacao(@Valid @RequestBody TransacaoDTO transacaoDTO) throws Exception {
        transacaoService.adicionarTransacao(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deletarTransacoes(){
        transacaoService.deletarTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<?> verificarEstatisticas(@RequestParam(defaultValue = "60") Integer segundos) {

    EstatisticaDTO estatisticas = transacaoService.estatisticasUltimosSegundos(segundos);
    return ResponseEntity.status(HttpStatus.OK).body(estatisticas);
}

}
