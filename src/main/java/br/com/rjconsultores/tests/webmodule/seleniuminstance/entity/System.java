package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.RequiredResourceException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.ValidateUtil;

public class System implements Entity {
	private String id;
	private String name;
	private String address;
	private String port;
	private String parentId;

	private String msgErrorScreenRequired;
	private String msgErrorScreenNullValue;

	private SourceEvent sourceEvent;

	private Collection<Screen> screens;

	public System() {
		screens = new LinkedHashSet<>();
		sourceEvent = SourceEvent.SYSTEM;

		msgErrorScreenRequired = "Uma ou mais telas devem estar associadas ao sistema.";
		msgErrorScreenNullValue = "A tela deve ser um valor válido e nulo não é um valor válido.";
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void registerScreen(Screen screen) {
		this.screens.add(screen);
	}

	public Collection<Screen> listScreens() {
		return screens;
	}
	
	@Override
	public void validate() throws SeleniumInstanceException {
		ValidateUtil.validateField(sourceEvent, "name", getName(), 80);
		ValidateUtil.validateField(sourceEvent, "address", getAddress(), 30);
		ValidateUtil.validateField(sourceEvent, "port", getPort(), 4);

		if (listScreens() == null || listScreens().isEmpty()) {
			throw new RequiredResourceException(sourceEvent, msgErrorScreenRequired);
		}

		for (Entity screen : listScreens()) {
			
			if (screen == null) {
				throw new RequiredResourceException(sourceEvent, msgErrorScreenNullValue);
			}

			screen.validate();
		}
	}	

	@Override
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public void generateIdsForChildrens() {
		Collection<Entity> entities = new LinkedHashSet<>();
		entities.addAll(listScreens());
		
		generateIdForEntity(entities);
	}
}
