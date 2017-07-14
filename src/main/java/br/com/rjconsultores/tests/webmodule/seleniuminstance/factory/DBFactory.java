package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Event;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Rule;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;

class DBFactory {
	private static DBFactory INSTANCE;

	private Collection<System> systems;

	public DBFactory() {
		systems = new LinkedHashSet<>();
	}
	
	static DBFactory INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new DBFactory();
		}

		return INSTANCE;
	}

	public Collection<System> getSystems() {
		return systems;
	}
	
	public Response registerSystem(System system) {
		String systemId = GenerateUtil.getIDForNewObject();
		system.setId(systemId);
		system.generateIdsForChildrens();
		system.listScreens().forEach(screen -> {generateIdsForChildrenScreen(screen);});
		
		systems.add(system);
		
		return new Response(system);
	}
	
	private void generateIdsForChildrenScreen(Screen screen) {
		screen.generateIdsForChildrens();
		screen.listComponents().forEach(component -> generateIdsforChildrenComponent(component));
	}
	
	private void generateIdsforChildrenComponent(Component component) {
		component.generateIdsForChildrens();
		component.listEvents().forEach(event -> generateIdsChildrenEvent(event));
	}
	
	private void generateIdsChildrenEvent(Event event) {
		event.generateIdsForChildrens();
		event.listRules().forEach(rule -> generateIdsChildrenRules(rule));
	}
	
	private void generateIdsChildrenRules(Rule rule) {
		rule.generateIdsForChildrens();
	}
	
	public Response updateSystem(System system) {
		if (!systems.contains(system)) {
			return registerSystem(system);
		}
		
		Iterator<System> systemIterator = systems.iterator();
		
		while (systemIterator.hasNext()) {
			//System system = (System) systemIterator.next();
		}
		
		return new Response(system);
	}
}