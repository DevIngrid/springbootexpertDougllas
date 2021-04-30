package io.github.dougllasfps.vendas.rest;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApiErrors {
	
	//o professor Dogllas tinha feito só assim
//	@Getter
//	private List<String> errors;
	
//	public ApiErrors(String mensagemErro) {
//		this.errors = Arrays.asList(mensagemErro);
//	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="GMT")
	private Instant timestamp;
	private Integer status;
	private String error;
	//private String message;
	private String path;
	
	private List<String> errors;
	
	public ApiErrors(Instant timestamp, Integer status, String error, List<String> errors, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		//this.message = message;
		this.errors = errors;
		this.path = path;
	}
	
	
	
	

}
