package io.github.dougllasfps.vendas.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.dougllasfps.vendas.domain.entity.Usuario;
import io.github.dougllasfps.vendas.rest.dto.UsuarioDTO;
import io.github.dougllasfps.vendas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioServiceImpl usuarioService;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO salvar (@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		Usuario user = usuarioService.salvar(usuario);
		return new UsuarioDTO(user);
		
	}

}
