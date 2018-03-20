package br.com.cinq.spring.data.sample.resource.exceptions;

public class InvalidLineException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLineException(String errorMessage) {
		super(errorMessage);
	}

}
