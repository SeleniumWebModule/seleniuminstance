package br.com.rjconsultores.tests.seleniuminstance.exception;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;

public class FieldSizeOverflowException extends SeleniumInstanceException {

	private static final long serialVersionUID = 6921528696676271341L;

	public FieldSizeOverflowException(SourceEvent sourceEvent, String fieldName) {
		super(sourceEvent, "O limite para o campo " + fieldName + " foi ultrapassado.");
	}
}
