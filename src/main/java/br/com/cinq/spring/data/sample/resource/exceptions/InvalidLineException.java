package br.com.cinq.spring.data.sample.resource.exceptions;

import java.util.List;

public class InvalidLineException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> listErrorMessages;
	public InvalidLineException(String errorMessage) {
		super(errorMessage);
	}
	public InvalidLineException(List<String> listErrorMessages) {
		this.listErrorMessages = listErrorMessages;
	}
	public List<String> getListErrorMessages() {
		return listErrorMessages;
	}
	@Override
	public String getMessage() {
		if(listErrorMessages==null || listErrorMessages.isEmpty())
			return super.getMessage();
		return createStringListErrorMessages();
	}
	private String createStringListErrorMessages() {
		StringBuilder errorMessages = new StringBuilder();
		String eol = System.getProperty("line.separator");
		for(String message:listErrorMessages) {
			String temp = message+eol;
			errorMessages.append(temp);
		}
		return errorMessages.toString();

	}
	
	
}
