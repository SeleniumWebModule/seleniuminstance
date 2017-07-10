package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.LinkedHashSet;
import java.util.Set;

public class Component {
	private String name;
	private String descricao;
	
	private Set<Attribute> attributes;
	private Set<Event> events;
	
	public Component() {
		attributes = new LinkedHashSet<>();
		events = new LinkedHashSet<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void registerAttribute(Attribute attribute) {
		attributes.add(attribute);
	}

	public Set<Attribute> listAttributes() {
		return attributes;
	}

	public void registerEvent(Event event) {
		events.add(event);
	}

	public Set<Event> listEvents() {
		return events;
	}
}
