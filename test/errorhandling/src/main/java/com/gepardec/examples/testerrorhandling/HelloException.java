package com.gepardec.examples.testerrorhandling;

public class HelloException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public int code;
	
	public HelloException(int code, String message) {
		super(message);
		this.code = code;
	}
}
