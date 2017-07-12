package br.com.rjconsultores.tests.seleniuminstance.exception;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;

public class RequiredResourceException extends SeleniumInstanceException {

	private static final long serialVersionUID = 652345497821021888L;

	public RequiredResourceException(SourceEvent sourceEvent, String message) {
		super(sourceEvent, message);
	}
}
