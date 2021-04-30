package io.github.dougllasfps.vendas.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.dougllasfps.vendas.domain.entity.Cliente;
import io.github.dougllasfps.vendas.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByCliente(Cliente cliente);
	
	@Query(" select p from Pedido p left join fetch p.items where p.id = :id")
	Optional<Pedido> findByIdFetchItems(Integer id);

}
