package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.ValidateUtil;

public class Screen implements Entity {
	private String name;
	
	private SourceEvent sourceEvent;
	
	private Collection<Attribute> attributes;
	private Collection<Component> components;
	private Collection<Event> events;
	
	
	public Screen() {
		attributes = new LinkedHashSet<>();
		components = new LinkedHashSet<>();
		events = new LinkedHashSet<>();
		
		sourceEvent = SourceEvent.SCREEN;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void registerAttribute(Attribute attribute) {
		attributes.add(attribute);	
	}
	
	public Collection<Attribute> listAttributes() {
		return attributes;
	}

	public Collection<Component> listComponents() {
		return components;
	}
	
	public Collection<Event> listEvents() {
		return events;
	}

	public void registerComponent(Component component) {
		components.add(component);
	}
	
	public void registerEvent(Event event) {
		events.add(event);
	}

	@Override
	public void validate() throws SeleniumInstanceException {
		ValidateUtil.validateField(sourceEvent, "name", getName(), 80);
		
		if (components == null || components.isEmpty()) {
			throw new ResourceRequiredException(sourceEvent, "No mínimo um componente deve estar associado à tela.");
		}
		
		for (Attribute attribute: listAttributes()) {
			attribute.validate();
		}
		
		for (Event event: listEvents()) {
			event.validate();
		}
		
		for (Component component: listComponents()) {
			component.validate();
		}
		
		
	}
}
