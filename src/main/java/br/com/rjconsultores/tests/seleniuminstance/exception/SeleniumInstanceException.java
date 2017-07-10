package br.com.rjconsultores.tests.seleniuminstance.exception;

public class SeleniumInstanceException extends Exception {

	private static final long serialVersionUID = 2457135409598748216L;
	
	private String message;
	private StackTraceElement stackException;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StackTraceElement getStackException() {
		return stackException;
	}

	public void setStackException(StackTraceElement stackException) {
		this.stackException = stackException;
	}
}
