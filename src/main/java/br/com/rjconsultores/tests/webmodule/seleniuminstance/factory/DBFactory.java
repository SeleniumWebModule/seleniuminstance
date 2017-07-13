package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

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
		systems.add(system);
		
		return new Response();
	}
	
	public Response updateSystem(System system) {
		if (!systems.contains(system)) {
			return registerSystem(system);
		}
		
		Iterator<System> systemIterator = systems.iterator();
		
		while (systemIterator.hasNext()) {
		
		}
		
		return new Response();
	}
}