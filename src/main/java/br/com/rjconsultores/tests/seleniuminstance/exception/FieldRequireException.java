package br.com.rjconsultores.tests.seleniuminstance.exception;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;

public class FieldRequireException extends SeleniumInstanceException {

	private static final long serialVersionUID = -5262838268363477536L;

	public FieldRequireException(SourceEvent sourceEvent, String fieldName) {
		super(sourceEvent, "O campo " + fieldName + " é requerido e precisa de um valor válido.");
	}
}
