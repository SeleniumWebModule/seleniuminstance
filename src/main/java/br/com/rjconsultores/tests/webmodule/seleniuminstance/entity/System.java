package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

public class System {
	private String name;
	private String address;
	private String port;
	
	private Collection<Screen> screens;
	
	public System() {
		screens = new LinkedHashSet<>();
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
}
