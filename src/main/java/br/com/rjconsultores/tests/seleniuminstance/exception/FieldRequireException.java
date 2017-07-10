package br.com.rjconsultores.tests.seleniuminstance.exception;

public class FieldRequireException extends SeleniumInstanceException {

	private static final long serialVersionUID = -5262838268363477536L;

	public FieldRequireException(String fieldName, StackTraceElement stackException) {
		super.setMessage("O campo " + fieldName + " é requerido e precisa de um valor válido.");
		super.setStackException(stackException);
	}
	
	public FieldRequireException(String fieldName) {
		super.setMessage("O campo " + fieldName + " é requerido e precisa de um valor válido.");
	}
}
