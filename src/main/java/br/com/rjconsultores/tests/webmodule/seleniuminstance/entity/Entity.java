package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;

public interface Entity {
	public void validate() throws SeleniumInstanceException;
}
