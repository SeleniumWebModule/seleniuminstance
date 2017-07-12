package br.com.rjconsultores.tests.seleniuminstance.exception;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;

public class FieldSizeOverflow extends SeleniumInstanceException {

	private static final long serialVersionUID = 6921528696676271341L;

	public FieldSizeOverflow(SourceEvent sourceEvent, String fieldName) {
		super(sourceEvent, "O limite para o campo " + fieldName + " foi ultrapassado.");
	}
}
