package com.panorbit.test.exceptionhandler;

public class DataInsertionFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataInsertionFailedException(String message) {
		super(message);
	}

}
