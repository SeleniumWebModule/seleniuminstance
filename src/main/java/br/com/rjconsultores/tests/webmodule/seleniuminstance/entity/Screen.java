package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Screen {
	private String name;
	private Collection<Attribute> attributes;
	private Collection<Component> components;
	
	public Screen() {
		attributes = new LinkedHashSet<>();
		components = new LinkedHashSet<>();
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

	public void registerComponent(Component component) {
		components.add(component);
	}
}
