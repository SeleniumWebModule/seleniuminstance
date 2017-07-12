package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.ValidateUtil;

public class Attribute implements Entity{
	
	private String name;
	private String description;
	private SourceEvent sourceEvent;
	
	public Attribute() {
		sourceEvent = SourceEvent.ATTRIBUTE;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void validate() throws SeleniumInstanceException {
		ValidateUtil.validateField(sourceEvent, "name", getName(), 30);
		ValidateUtil.validateField(sourceEvent, "description", getDescription(), 400);
	}
}
