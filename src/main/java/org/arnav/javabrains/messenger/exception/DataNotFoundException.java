package org.arnav.javabrains.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * Add serial Id as runtime exceptions has a serial ID, Add generated version
	 */
	private static final long serialVersionUID = -4650764117253331382L;


	public DataNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}