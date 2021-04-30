package io.github.dougllasfps.vendas.rest.controller;


import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.dougllasfps.vendas.domain.entity.Pedido;
import io.github.dougllasfps.vendas.domain.enums.StatusPedido;
import io.github.dougllasfps.vendas.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.dougllasfps.vendas.rest.dto.InformacoesPedidoDTO;
import io.github.dougllasfps.vendas.rest.dto.PedidoDTO;
import io.github.dougllasfps.vendas.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	
	private PedidoService service;

	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Integer save ( @RequestBody @Valid PedidoDTO dto ) {
		
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}
	
	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return service
				.obterPedidoCompleto(id)
				.map(p -> new InformacoesPedidoDTO(p))
				.orElseThrow(() -> 
				         new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
		
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
		String novoStatus = dto.getNovoStatus();
		service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
	}
	
	//O professor fez desta forma, mas eu quiz testar o jeito que o Nelio ensinou e fiz diferente
	//Estes métodos foram criados para o método getById
//	private InformacoesPedidoDTO converter(Pedido pedido) {
//		return InformacoesPedidoDTO
//			   .builder().codigo(pedido.getId())
//			   .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
//			   .cpf(pedido.getCliente().getCpf())
//			   .nomeCliente(pedido.getCliente().getNome())
//			   .total(pedido.getTotal())
//			   .items(converter(pedido.getItems()))
//			   .build();
//	}
	
//	private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> items){
//		if(CollectionUtils.isEmpty(items)) {
//			return Collections.emptyList();
//		}
//		return items.stream().map(
//				item -> InformacaoItemPedidoDTO
//					.builder().descricaoProduto(item.getProduto().getDescricao())
//					.precoUnitario(item.getProduto().getPreco())
//					.quantidade(item.getQuantidade())
//					.build()
//				).collect(Collectors.toList());
//		
//	}
	
	

}
