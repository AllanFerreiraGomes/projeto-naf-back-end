package com.example.projeto_naf_back.exceptions;

public class InsufficientException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InsufficientException(String message) {
		super();
	}
	
	public InsufficientException(Integer id, String produto) {
		super("Estoque do produto " + produto + " insuficiente.");
	}

}
