package io.github.dougllasfps.vendas.rest.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import io.github.dougllasfps.vendas.domain.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {
	private Integer codigo;
	private String cpf;
	private String nomeCliente;
	private BigDecimal total;
	private String dataPedido;
	private String status;
	private List<InformacaoItemPedidoDTO> items;
	
	public InformacoesPedidoDTO(Pedido pedido) {
		this.codigo=pedido.getId();
		this.cpf = pedido.getCliente().getCpf();
		this.nomeCliente=pedido.getCliente().getNome();
		this.total=pedido.getTotal();
		this.dataPedido=pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.status = pedido.getStatus().name();
		this.items=pedido.getItems().stream().map(i -> new InformacaoItemPedidoDTO(i))
				.collect(Collectors.toList());
	}

}
