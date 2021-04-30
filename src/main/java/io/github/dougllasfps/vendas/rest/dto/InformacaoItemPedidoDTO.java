package io.github.dougllasfps.vendas.rest.dto;

import java.math.BigDecimal;

import io.github.dougllasfps.vendas.domain.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoItemPedidoDTO {
	private String descricaoProduto;
	private BigDecimal precoUnitario;
	private Integer quantidade;
	
	public InformacaoItemPedidoDTO(ItemPedido itemPedido) {
		this.descricaoProduto=itemPedido.getProduto().getDescricao();
		this.precoUnitario=itemPedido.getProduto().getPreco();
		this.quantidade= itemPedido.getQuantidade();
	}
		

}
