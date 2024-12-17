package com.RenoveSkinContact.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.RenoveSkinContact.entities.Cliente;
import com.RenoveSkinContact.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	// Listar todos os clientes;
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	// Buscar cliente por Id;
	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	// Criar um novo cliente;
	public Cliente criar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	// Atualizar um cliente existente;
	public Optional<Cliente> atualizar(Long id, Cliente clienteAtualizado) {
		// Busca o cliente pelo ID no banco de dados
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);

		// Verifica se o cliente foi encontrado
		if (clienteOptional.isPresent()) {
			// Obtém o cliente encontrado
			Cliente cliente = clienteOptional.get();

			// Atualiza os campos do cliente com os dados recebidos
			cliente.setNome(clienteAtualizado.getNome());
			cliente.setCidade(clienteAtualizado.getCidade());
			cliente.setDataCompra(clienteAtualizado.getDataCompra());
			cliente.setAtivo(clienteAtualizado.isAtivo());

			// Salva o cliente atualizado no banco de dados
			Cliente clienteAtualizadoSalvo = clienteRepository.save(cliente);

			// Retorna o cliente salvo dentro de um Optional
			return Optional.of(clienteAtualizadoSalvo);
		}

		// Retorna um Optional vazio caso o cliente não tenha sido encontrado
		return Optional.empty();
	}

	// Deletar um cliente;
	public String deletar(Long id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return "Cliente deletado com sucesso";
		}
		return "Cliente não encontrado para exclusão!";
	}
	
	
//	public List<Cliente> buscarClientesParaContato() {
//	    LocalDate hoje = LocalDate.now();
//	    return clienteRepository.findAll().stream()
//	        .filter(cliente -> {
//	            LocalDate dataCompra = cliente.getDataCompra();
//	            if (dataCompra != null) {
//	                long mesesDesdeCompra = ChronoUnit.MONTHS.between(dataCompra, hoje);
//	                return mesesDesdeCompra > 0 && mesesDesdeCompra % 3 == 0;
//	            }
//	            return false;
//	        })
//	        .collect(Collectors.toList());
//	}
	
	
	public List<Cliente> buscarClientesParaContato() {
	    LocalDate hoje = LocalDate.now();
	    return clienteRepository.findAll().stream()
	        .filter(cliente -> cliente.isAtivo()) // Adiciona o filtro para clientes ativos
	        .filter(cliente -> {
	            LocalDate dataCompra = cliente.getDataCompra();
	            if (dataCompra != null) {
	                long mesesDesdeCompra = ChronoUnit.MONTHS.between(dataCompra, hoje);
	                return mesesDesdeCompra > 0 && mesesDesdeCompra % 3 == 0;
	            }
	            return false;
	        })
	        .collect(Collectors.toList());
	}

	
	
	// Método para atualizar apenas o status
	public void atualizarStatus(Long id, boolean ativo) {
	    Cliente cliente = clienteRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	    cliente.setAtivo(ativo);
	    clienteRepository.save(cliente);
	}
	
	// Busca cliente por nome
	public List<Cliente> buscarPorNome(String nome) {
	    return clienteRepository.findByNomeContainingIgnoreCase(nome);
	}


}
