package br.com.rjconsultores.tests.seleniuminstance.exception;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;

public class SeleniumInstanceException extends Exception {

	private static final long serialVersionUID = 2457135409598748216L;
	
	private String message;
	private SourceEvent sourceEvent;
	private StackTraceElement stackException;

	public SeleniumInstanceException(SourceEvent sourceEvent, String message) {
		this.sourceEvent = sourceEvent;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public SourceEvent getSourceEvent() {
		return sourceEvent;
	}

	public StackTraceElement getStackException() {
		return stackException;
	}
}
