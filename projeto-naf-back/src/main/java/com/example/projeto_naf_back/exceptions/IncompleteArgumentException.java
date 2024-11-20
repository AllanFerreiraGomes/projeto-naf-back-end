package com.example.projeto_naf_back.exceptions;

public class IncompleteArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IncompleteArgumentException(String message) {
		super(message);
	}
	
	public IncompleteArgumentException() {
		super("Informações faltantes no corpo da requisição");
	}
}
