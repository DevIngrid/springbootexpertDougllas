package io.github.dougllasfps.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.dougllasfps.vendas.domain.entity.Cliente;
import io.github.dougllasfps.vendas.domain.entity.ItemPedido;
import io.github.dougllasfps.vendas.domain.entity.Pedido;
import io.github.dougllasfps.vendas.domain.entity.Produto;
import io.github.dougllasfps.vendas.domain.enums.StatusPedido;
import io.github.dougllasfps.vendas.domain.repositories.ClienteRepository;
import io.github.dougllasfps.vendas.domain.repositories.ItemPedidoRepository;
import io.github.dougllasfps.vendas.domain.repositories.PedidoRepository;
import io.github.dougllasfps.vendas.domain.repositories.ProdutoRepository;
import io.github.dougllasfps.vendas.exception.PedidoNaoEncontradoException;
import io.github.dougllasfps.vendas.exception.RegraNegocioException;
import io.github.dougllasfps.vendas.rest.dto.ItemPedidoDTO;
import io.github.dougllasfps.vendas.rest.dto.PedidoDTO;
import io.github.dougllasfps.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{

	private final PedidoRepository repository;
	private final ClienteRepository clientesRepository;
	private final ProdutoRepository produtosRepository;
	private final ItemPedidoRepository itemPedidoRepository;

	
	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(()-> 
		new RegraNegocioException("Código de cliente inválido."));
		
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		repository.save(pedido);
		itemPedidoRepository.saveAll(itemsPedido);
		
		pedido.setItems(itemsPedido);
		
		return pedido;
	}
	
	//para converter ItemPedidoDTO em ItemPedido
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if(items.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
		}
		
		return items
				.stream()
				.map ( dto -> {
					Integer idProduto = dto.getProduto();
					Produto produto = produtosRepository.findById(idProduto)
					.orElseThrow(()-> new RegraNegocioException("Código de produto inválido: "+ idProduto));
					
					ItemPedido itemPedido = new ItemPedido();
					
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					
					return itemPedido;
					
				}).collect(Collectors.toList());
	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		
		return repository.findByIdFetchItems(id);
	}

	@Override
	public void atualizaStatus(Integer id, StatusPedido statusPedido) {
		repository.findById(id)
					.map(pedido -> {
						pedido.setStatus(statusPedido);
						repository.save(pedido);
						return pedido;
					}).orElseThrow(() -> new PedidoNaoEncontradoException());
		
	}
	
	
	
	
}
