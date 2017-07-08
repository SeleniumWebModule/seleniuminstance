package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;

class InstanceFactory {
	private static InstanceFactory INSTANCE;
	private static Set<System> systems;
	private static Set<Screen> screens;
	
	static InstanceFactory INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new InstanceFactory();
			systems = new LinkedHashSet<>();
			screens = new LinkedHashSet<>();
		}
		
		return INSTANCE;
	}
	
	public static Set<System> getSystems() {
		return systems;
	}
	
	public static Set<Screen> getScreens() {
		return screens;
	}
}
