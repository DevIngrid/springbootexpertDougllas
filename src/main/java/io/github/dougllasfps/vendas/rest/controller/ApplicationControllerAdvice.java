package io.github.dougllasfps.vendas.rest.controller;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.dougllasfps.vendas.exception.PedidoNaoEncontradoException;
import io.github.dougllasfps.vendas.exception.RegraNegocioException;
import io.github.dougllasfps.vendas.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex, HttpServletRequest request) {
		List<String> mensagemErro = Arrays.asList(ex.getMessage());
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Requisição inválida";
		return new ApiErrors(Instant.now(),status.value(),error, mensagemErro, 
				request.getRequestURI());
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex, HttpServletRequest request) {
		List<String> mensagemErro = Arrays.asList(ex.getMessage());
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Requisição inválida";
		return new ApiErrors(Instant.now(),status.value(),error, mensagemErro, 
				request.getRequestURI());
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream().map( erro -> erro.getDefaultMessage())
		.collect(Collectors.toList());
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Requisição inválida";
		return new ApiErrors(Instant.now(),status.value(),error,errors, 
				request.getRequestURI());
	}

}
