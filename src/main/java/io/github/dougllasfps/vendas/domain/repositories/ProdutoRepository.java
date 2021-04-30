package io.github.dougllasfps.vendas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dougllasfps.vendas.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
