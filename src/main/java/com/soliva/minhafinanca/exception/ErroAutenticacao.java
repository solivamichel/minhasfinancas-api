package com.soliva.minhafinanca.exception;

public class ErroAutenticacao extends RuntimeException {
	private static final long serialVersionUID = 4377600450312320524L;
	
	public ErroAutenticacao(String msg) {
		super(msg);
	}
}
