package io.github.dougllasfps.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 
 *  "produto":1,
            "quantidade":10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
	private Integer produto;
	private Integer quantidade;
}
