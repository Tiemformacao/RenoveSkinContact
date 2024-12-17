package com.RenoveSkinContact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
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
	

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
	    try {
	        historicoService.deletar(id);
	        return ResponseEntity.noContent().build(); // Retorna 204 No Content
	    } catch (RuntimeException e) {
	        return ResponseEntity.notFound().build(); // Retorna 404 se o histórico não for encontrado
	    }
	}




}
