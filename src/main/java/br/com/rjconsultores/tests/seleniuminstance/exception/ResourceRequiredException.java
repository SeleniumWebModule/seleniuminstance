package br.com.rjconsultores.tests.seleniuminstance.exception;

public class ResourceRequiredException extends SeleniumInstanceException {

	private static final long serialVersionUID = 652345497821021888L;

	public ResourceRequiredException(String message, StackTraceElement stackException) {
		super.setMessage(message);
		super.setStackException(stackException);
	}

	public ResourceRequiredException(String message) {
		super.setMessage(message);
	}
}
