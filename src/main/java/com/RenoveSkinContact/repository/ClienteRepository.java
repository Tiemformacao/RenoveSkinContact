package com.RenoveSkinContact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RenoveSkinContact.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	    // Containing: Busca parcial no nome.
	    // IgnoreCase: Não diferencia maiúsculas de minúsculas.
	    List<Cliente> findByNomeContainingIgnoreCase(String nome);

}
