package com.RenoveSkinContact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RenoveSkinContact.entities.HistoricoInteracao;
import com.RenoveSkinContact.repository.HistoricoInteracaoRepository;

@Service
public class HistoricoInteracaoService {

	@Autowired
	private HistoricoInteracaoRepository historicoInteracaoRepository;
	
	// Listar interações de um cliente;
	public List<HistoricoInteracao> listarPorCliente(Long clienteId){
		return historicoInteracaoRepository.findByClienteId(clienteId);
	}
	
	// Criar uma nova interação
	public HistoricoInteracao criar(HistoricoInteracao historico) {
		return historicoInteracaoRepository.save(historico);
	}
	
	public String deletar(Long id) {
	    if (historicoInteracaoRepository.existsById(id)) {
	        historicoInteracaoRepository.deleteById(id);
	        return "Histórico deletado com sucesso";
	    }
	    return "Histórico não encontrado para exclusão";
	}
}
