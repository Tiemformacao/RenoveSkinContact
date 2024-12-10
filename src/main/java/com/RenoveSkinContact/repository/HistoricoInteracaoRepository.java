package com.RenoveSkinContact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RenoveSkinContact.entities.HistoricoInteracao;

@Repository
public interface HistoricoInteracaoRepository extends JpaRepository<HistoricoInteracao, Long> {
	
	List<HistoricoInteracao> findByClienteId(Long clienteId);
}
