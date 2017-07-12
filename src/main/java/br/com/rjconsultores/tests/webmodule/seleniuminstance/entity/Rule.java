package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;

public class Rule implements Entity{
	private String name;
	private String description;
	
	private Collection<Attribute> attributes;
	
	private SourceEvent sourceEvent;
	
	public Rule() {
		attributes = new LinkedHashSet<>();
		sourceEvent = SourceEvent.RULE;
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
	
	public void registerAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
	public Collection<Attribute> listAttributes() {
		return attributes;
	}

	@Override
	public void validate() throws SeleniumInstanceException {
		// TODO Auto-generated method stub
		
	}
}
