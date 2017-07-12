package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.ValidateUtil;

public class System implements Entity{
	private String name;
	private String address;
	private String port;
	
	private SourceEvent sourceEvent;
	
	private Collection<Screen> screens;
	
	public System() {
		screens = new LinkedHashSet<>();
		sourceEvent = SourceEvent.SYSTEM;
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
			throw new ResourceRequiredException(sourceEvent, "No mínimo uma tela deve estar associada ao sistema.");
		}
		
		for (Screen screen: listScreens()) {
			if (screen == null) {
				throw new ResourceRequiredException(sourceEvent, "Uma tela é esperada como parametro para o sistema, foi encontrado null.");
			}
			
			screen.validate();
		}
	}
}
