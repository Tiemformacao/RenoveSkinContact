package com.RenoveSkinContact.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RenoveSkinContact.entities.Cliente;
import com.RenoveSkinContact.service.ClienteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	// Listar todos os clientes;
	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos() {
		List<Cliente> clientes = clienteService.listarTodos();
		return ResponseEntity.ok(clientes);
	}

	// Buscar cliente por Id;
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.buscarPorId(id);
		// Verifica se o cliente está presente no Optional
		if (cliente.isPresent()) {
			// Retorna o cliente com status 200 OK
			return ResponseEntity.ok(cliente.get());
		} else {
			// Retorna status 404 Not Found se o cliente não foi encontrado
			return ResponseEntity.notFound().build();
		}
	}

	// Criar um novo cliente;
	@PostMapping
	public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
		Cliente novoCliente = clienteService.criar(cliente);
		return ResponseEntity.ok(novoCliente);
	}

	// Atualizar um cliente existente;
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
		Optional<Cliente> cliente = clienteService.atualizar(id, clienteAtualizado);
		// Verifica se o cliente atualizado está presente no Optional
		if (cliente.isPresent()) {
			// Retorna o cliente atualizado com status 200 OK
			return ResponseEntity.ok(cliente.get());
		} else {
			// Retorna status 404 Not Found se o cliente não foi encontrado
			return ResponseEntity.notFound().build();
		}
	}
	
	// Deletar um cliente
	@DeleteMapping("/{id}")
	public ResponseEntity<String>  deletar(@PathVariable Long id) {
		String mensagem = clienteService.deletar(id);
		if(mensagem.equals("Cliente deletado com sucesso")) {
			return ResponseEntity.ok(mensagem);
		}
		return ResponseEntity.status(404).body("Cliente com ID " + id + " não foi encontrado!");
	}
	
	@GetMapping("/para-contato")
	public ResponseEntity<List<Cliente>> clientesParaContato() {
	    List<Cliente> clientes = clienteService.buscarClientesParaContato();
	    return ResponseEntity.ok(clientes);
	}
	
	
	// Método para atualizar apenas o status
	@PutMapping("/{id}/status")
	public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestParam boolean ativo) {
	    clienteService.atualizarStatus(id, ativo);
	    return ResponseEntity.ok().build();
	}
	
	// Buscar cliente pelo nome
	@GetMapping("/buscar")
	public ResponseEntity<List<Cliente>> buscarClientesPorNome(@RequestParam String nome) {
	    List<Cliente> clientes = clienteService.buscarPorNome(nome);
	    return ResponseEntity.ok(clientes);
	}

}
