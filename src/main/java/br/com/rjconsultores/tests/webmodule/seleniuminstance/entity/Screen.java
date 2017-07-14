package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.RequiredResourceException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.ValidateUtil;

public class Screen implements Entity {
	private String id;
	private String parentId;
	private String name;
	
	private SourceEvent sourceEvent;
	
	private Collection<Attribute> attributes;
	private Collection<Component> components;
	private Collection<Event> events;
	
	private String msgErrorComponentRequired;
	private String msgErrorAttributeNullValue;
	private String msgErrorEventNullValue;
	
	private final int SIZE_FIELD_NAME = 80; 
	
	
	public Screen() {
		attributes = new LinkedHashSet<>();
		components = new LinkedHashSet<>();
		events = new LinkedHashSet<>();
		
		sourceEvent = SourceEvent.SCREEN;
		
		msgErrorComponentRequired = "Um ou mais componentes devem estar associados à tela.";
		msgErrorAttributeNullValue = "O atributo deve ser um valor válido e nulo não é um valor válido.";
		msgErrorEventNullValue = "O evento deve ser um valor válido e nulo não é um valor válido.";
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	@Override
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
		ValidateUtil.validateField(sourceEvent, "name", getName(), SIZE_FIELD_NAME);
		
		if (components == null || components.isEmpty()) {
			throw new RequiredResourceException(sourceEvent, msgErrorComponentRequired);
		}
		
		for (Attribute attribute: listAttributes()) {
			if (attribute == null) {
				throw new RequiredResourceException(sourceEvent, msgErrorAttributeNullValue);
			}
			
			attribute.validate();
		}
		
		for (Event event: listEvents()) {
			if (event == null) {
				throw new RequiredResourceException(sourceEvent, msgErrorEventNullValue);
			}
			
			event.validate();
		}
		
		for (Component component: listComponents()) {
			if (component == null) {
				throw new RequiredResourceException(sourceEvent, msgErrorComponentRequired);
			}
			
			component.validate();
		}
	}

	@Override
	public void generateIdsForChildrens() {
		Collection<Entity> entitiesComponents = new LinkedHashSet<>();
		Collection<Entity> entitiesEvents = new LinkedHashSet<>();
		Collection<Entity> entitiesAttributes = new LinkedHashSet<>();
		
		entitiesComponents.addAll(listComponents());
		entitiesEvents.addAll(listEvents());
		entitiesAttributes.addAll(listAttributes());
		
		generateIdForEntity(entitiesComponents);
		generateIdForEntity(entitiesEvents);
		generateIdForEntity(entitiesAttributes);	
	}
}
