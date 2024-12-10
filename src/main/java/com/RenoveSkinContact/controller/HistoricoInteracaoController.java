package com.RenoveSkinContact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RenoveSkinContact.entities.HistoricoInteracao;
import com.RenoveSkinContact.service.HistoricoInteracaoService;

@RestController
@RequestMapping("/api/historicos")
public class HistoricoInteracaoController {

	@Autowired
	private HistoricoInteracaoService historicoService;

	// Listar interações de um cliente
	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<List<HistoricoInteracao>> listarPorCliente(@PathVariable Long clienteId) {
		List<HistoricoInteracao> historicos = historicoService.listarPorCliente(clienteId);
		return ResponseEntity.ok(historicos);
	}

	// Criar uma nova interação
	@PostMapping
	public ResponseEntity<HistoricoInteracao> criar(@RequestBody HistoricoInteracao historico) {
		HistoricoInteracao novoHistorico = historicoService.criar(historico);
		return ResponseEntity.ok(novoHistorico);
	}
	
	// Deletar uma interação
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
	    String mensagem = historicoService.deletar(id);
	    if (mensagem.equals("Histórico deletado com sucesso")) {
	        return ResponseEntity.ok(mensagem);
	    }
	    return ResponseEntity.status(404).body(mensagem);
	}


}
