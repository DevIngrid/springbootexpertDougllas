package io.github.dougllasfps.vendas.service;

import java.util.Optional;

import io.github.dougllasfps.vendas.domain.entity.Pedido;
import io.github.dougllasfps.vendas.domain.enums.StatusPedido;
import io.github.dougllasfps.vendas.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar ( PedidoDTO dto );
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	void atualizaStatus (Integer id, StatusPedido statusPedido);

}
