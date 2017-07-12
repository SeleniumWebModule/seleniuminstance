package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.ValidateUtil;

public class Component implements Entity{
	private String name;
	private String description;

	private Collection<Attribute> attributes;
	private Collection<Event> events;
	
	private SourceEvent sourceEvent;
	
	public Component() {
		attributes = new LinkedHashSet<>();
		events = new LinkedHashSet<>();
		sourceEvent = SourceEvent.COMPONENT;
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

	public void registerEvent(Event event) {
		events.add(event);
	}

	public Collection<Event> listEvents() {
		return events;
	}

	@Override
	public void validate() throws SeleniumInstanceException {
		ValidateUtil.validateField(sourceEvent, "name", getName(), 30);
		ValidateUtil.validateField(sourceEvent, "description", getDescription(), 400);
		
		if (listEvents() == null || listEvents().isEmpty()) {
			throw new ResourceRequiredException(sourceEvent, "No mínimo um evento deve estar associado ao componente.");
		}
		
		for (Event event: listEvents()) {
			event.validate();
		}
		
		for (Attribute attribute: listAttributes()) {
			attribute.validate();
		}
	}
}
