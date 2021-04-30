package io.github.dougllasfps.vendas.rest.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.dougllasfps.vendas.domain.entity.Cliente;
import io.github.dougllasfps.vendas.domain.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private ClienteRepository clientes;
	
	public ClienteController(ClienteRepository clientes) {
		this.clientes=clientes;
	}
	
	@GetMapping("{id}")
	public Cliente getClientesById( @PathVariable Integer id ) {
		return clientes
				.findById(id)
				.orElseThrow(()->
					new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save (@RequestBody @Valid Cliente cliente) {
		return clientes.save(cliente);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable Integer id ) {
		clientes.findById(id)
			.map(cliente -> {
				clientes.delete(cliente);
				return cliente;
			}).orElseThrow(()->
			new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Cliente não encontrado"));
			
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update ( @PathVariable Integer id,
								   @RequestBody @Valid Cliente cliente) {
		clientes.findById(id).map( clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clientes.save(cliente);
			return clienteExistente;
		}).orElseThrow(()->
		new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Cliente não encontrado"));
	}
	
	//fiz um pouco diferente do professor ... acrescentei paginação
	@GetMapping
	public Page<Cliente> find (Cliente filtro, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher
				                   .matching()
				                   .withIgnoreCase()
				                   .withStringMatcher(StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(filtro, matcher);
		Page<Cliente> lista = clientes.findAll(example,pageable);
		return lista;
	}
	
	//só paginação - fiz por conta própria
	@GetMapping(value = "/clientes/page")
	public ResponseEntity<Page<Cliente>> findPage (Pageable pageable) {
		Page<Cliente> listaPaginada = clientes.findAll(pageable);
		return ResponseEntity.ok(listaPaginada);
	}
	
	//fiz uma busca por filtro - vou precisar dela no camera8
	@GetMapping(value="/clientesFiltro")
	public ResponseEntity<Page<Cliente>> buscaPorFiltro(
			@RequestParam(defaultValue = "") String filtro,
			Pageable pageable) {
		
		Page<Cliente> listaFiltrada = clientes.buscaFiltrada(filtro, pageable);
		return ResponseEntity.ok(listaFiltrada);
		
	}

}
