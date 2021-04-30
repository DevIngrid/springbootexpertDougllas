package io.github.dougllasfps.vendas.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import io.github.dougllasfps.vendas.domain.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	List<Cliente> findByNomeLike(String nome);
	
	boolean existsByNome(String nome);
	
	//usando @Query
	
	@Query(value = " select c from Cliente c where c.nome like :nome ")
	List<Cliente> encontrarPorNome(@Param ("nome") String nome);
	
	@Query(" delete from Cliente c where c.nome = :nome ")
	@Modifying
	@Transactional
	void deletarPorNome(String nome);
	
	//usando sql nativo
	@Query(value = " select * from cliente  where nome like :nome ", nativeQuery=true)
	List<Cliente> encontrarPorNomeNativo(@Param ("nome") String nome);
	
	//Buscar Cliente com seus pedidos
	@Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
	
	//para minha busca filtrada
	@Query(" select c from Cliente c where lower(c.nome) like lower (concat('%', :filtro,'%')) "
			+ "or c.cpf like (concat('%', :filtro,'%'))")
	Page<Cliente> buscaFiltrada(String filtro, Pageable pageable);
	

}
