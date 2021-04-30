package io.github.dougllasfps.vendas.rest.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.dougllasfps.vendas.domain.entity.Produto;
import io.github.dougllasfps.vendas.domain.repositories.ProdutoRepository;

@RestController
@RequestMapping(value = "api/produtos")
public class ProdutoController {
	
	private ProdutoRepository produtos;
	
	public ProdutoController (ProdutoRepository produtos) {
		this.produtos = produtos;
	}
	
	@GetMapping(value= "/{id}")
	public Produto buscaPorId (@PathVariable Integer id) {
		return produtos.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save (@RequestBody @Valid Produto produto) {
		return produtos.save(produto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Integer id) {
		produtos.findById(id).map(produto -> {
			produtos.delete(produto);
			return produto;
			}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, 
					"Produto não encontrado"));	
	}
	
	//fiz diferente do professor
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id,
			           @RequestBody @Valid Produto produto) {
		try {
			Produto produtoExistente = produtos.getOne(id);
			produtoExistente.setDescricao(produto.getDescricao());
			produtoExistente.setPreco(produto.getPreco());
			produtos.save(produtoExistente);
			
		}catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id inválido");
		}
	}
	
	@GetMapping
	public Page<Produto> find (Produto filtro, Pageable pageable){
		ExampleMatcher matcher = ExampleMatcher
								.matching()
								.withIgnoreCase()
								.withStringMatcher(StringMatcher.CONTAINING);
		Example<Produto>example= Example.of(filtro, matcher);
		Page<Produto> listaProdutos = produtos.findAll(example, pageable);
		return listaProdutos;
	}

}
