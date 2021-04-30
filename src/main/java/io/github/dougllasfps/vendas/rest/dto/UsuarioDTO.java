package io.github.dougllasfps.vendas.rest.dto;

import io.github.dougllasfps.vendas.domain.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	
	private Integer id;
	private String login;
	
	public UsuarioDTO(Usuario usuario) {
		this.id=usuario.getId();
		this.login=usuario.getLogin();
	}

}
